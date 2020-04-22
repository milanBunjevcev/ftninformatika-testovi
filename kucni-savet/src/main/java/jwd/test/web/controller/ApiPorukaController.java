package jwd.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.test.model.Poruka;
import jwd.test.model.Glas;
import jwd.test.service.PorukaService;
import jwd.test.support.PorukaDTOToPoruka;
import jwd.test.support.PorukaToPorukaDTO;
import jwd.test.support.GlasToGlasDTO;
import jwd.test.web.dto.PorukaDTO;
import jwd.test.web.dto.GlasDTO;

@RestController
@RequestMapping(value = "/api/poruke")
public class ApiPorukaController {
	@Autowired
	private PorukaService porukaService;

	@Autowired
	private PorukaToPorukaDTO toDTO;
	@Autowired
	private PorukaDTOToPoruka toModel1;

	@Autowired
	private GlasToGlasDTO toModel3DTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PorukaDTO>> get(@RequestParam(required = false) Long zgradaId,
			@RequestParam(required = false) String naslov, @RequestParam(required = false) String tip,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "rowsPerPage", defaultValue = "5") int rowsPerPage) {

		Page<Poruka> porukePage = null;

		if (naslov != null || zgradaId != null || tip != null) {
			porukePage = porukaService.search(zgradaId, naslov, tip, pageNum, rowsPerPage);
		} else {
			porukePage = porukaService.findAll(pageNum, rowsPerPage);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(porukePage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(porukePage.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PorukaDTO> getOne(@PathVariable Long id) {
		Poruka poruka = porukaService.findOne(id);
		if (poruka == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(poruka), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PorukaDTO> delete(@PathVariable Long id) {
		Poruka deleted = porukaService.delete(id);
		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PorukaDTO> add(@Validated @RequestBody PorukaDTO newModel1DTO) {
		Poruka savedModel1 = porukaService.save(toModel1.convert(newModel1DTO));
		return new ResponseEntity<>(toDTO.convert(savedModel1), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<PorukaDTO> edit(@Validated @RequestBody PorukaDTO porukaDTO, @PathVariable Long id) {
		if (!id.equals(porukaDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Poruka persisted = porukaService.save(toModel1.convert(porukaDTO));
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}", consumes = "application/json")
	public ResponseEntity<GlasDTO> interact(@PathVariable Long id, @RequestBody GlasDTO glasDTO) {
		Glas glas = porukaService.interact(id, glasDTO.getPredlogPodrzan());
		// TODO prilagoditi
		if (glas != null) {
			return new ResponseEntity<GlasDTO>(toModel3DTO.convert(glas), HttpStatus.OK);
		}

		return new ResponseEntity<GlasDTO>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}