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

import jwd.test.model.Zadatak;
import jwd.test.model.Stanje;
import jwd.test.service.ZadatakService;
import jwd.test.support.ZadatakDTOToZadatak;
import jwd.test.support.ZadatakToZadatakDTO;
import jwd.test.support.StanjeToStanjeDTO;
import jwd.test.web.dto.ZadatakDTO;
import jwd.test.web.dto.StanjeDTO;

@RestController
@RequestMapping(value = "/api/zadaci")
public class ApiZadatakController {
	@Autowired
	private ZadatakService zadatakService;

	@Autowired
	private ZadatakToZadatakDTO toDTO;
	@Autowired
	private ZadatakDTOToZadatak toZadatak;

	@Autowired
	private StanjeToStanjeDTO toModel3DTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ZadatakDTO>> get(
			@RequestParam(required = false) String ime,
			@RequestParam(required = false) Long sprintId, 
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "rowsPerPage", defaultValue = "5") int rowsPerPage) {

		Page<Zadatak> zadatakPage = null;

		if (ime != null || sprintId != null) {
			zadatakPage = zadatakService.search(ime, sprintId, pageNum, rowsPerPage);
		} else {
			zadatakPage = zadatakService.findAll(pageNum, rowsPerPage);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(zadatakPage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(zadatakPage.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ZadatakDTO> getOne(@PathVariable Long id) {
		Zadatak zadatak = zadatakService.findOne(id);
		if (zadatak == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(zadatak), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ZadatakDTO> delete(@PathVariable Long id) {
		Zadatak deleted = zadatakService.delete(id);
		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ZadatakDTO> add(@Validated @RequestBody ZadatakDTO newModel1DTO) {
		Zadatak savedModel1 = zadatakService.save(toZadatak.convert(newModel1DTO));
		return new ResponseEntity<>(toDTO.convert(savedModel1), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<ZadatakDTO> edit(@Validated @RequestBody ZadatakDTO zadatakDTO, @PathVariable Long id) {
		if (!id.equals(zadatakDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Zadatak persisted = zadatakService.save(toZadatak.convert(zadatakDTO));
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ResponseEntity<StanjeDTO> interact(@PathVariable Long id) {
		Stanje stanje = zadatakService.interact(id);
		// TODO prilagoditi
		if (stanje != null) {
			return new ResponseEntity<StanjeDTO>(toModel3DTO.convert(stanje), HttpStatus.OK);
		}

		return new ResponseEntity<StanjeDTO>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}