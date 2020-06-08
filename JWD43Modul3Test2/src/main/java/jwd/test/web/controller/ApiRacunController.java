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

import jwd.test.model.Racun;
import jwd.test.model.TipRacuna;
import jwd.test.service.RacunService;
import jwd.test.support.RacunDTOToRacun;
import jwd.test.support.RacunToRacunDTO;
import jwd.test.support.TipRacunToTipRacunaDTO;
import jwd.test.web.dto.RacunDTO;
import jwd.test.web.dto.TipRacunaDTO;

@RestController
@RequestMapping(value = "/api/racuni")
public class ApiRacunController {
	@Autowired
	private RacunService racunService;

	@Autowired
	private RacunToRacunDTO toDTO;
	@Autowired
	private RacunDTOToRacun toModel1;

	@Autowired
	private TipRacunToTipRacunaDTO toModel3DTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RacunDTO>> get(@RequestParam(required = false) String jmbg,
			@RequestParam(required = false) Long bankaId,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "rowsPerPage", defaultValue = "5") int rowsPerPage) {

		Page<Racun> linijePage = null;

		if (jmbg != null || bankaId != null) {
			linijePage = racunService.search(jmbg, bankaId, pageNum, rowsPerPage);
		} else {
			linijePage = racunService.findAll(pageNum, rowsPerPage);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linijePage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(linijePage.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RacunDTO> getOne(@PathVariable Long id) {
		Racun racun = racunService.findOne(id);
		if (racun == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(racun), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<RacunDTO> delete(@PathVariable Long id) {
		Racun rac = racunService.findOne(id);
		if (rac != null) {
			if (rac.getStanjeRacuna() != 0) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		}

		Racun deleted = racunService.delete(id);
		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RacunDTO> add(@Validated @RequestBody RacunDTO newModel1DTO) {
		Racun savedModel1 = racunService.save(toModel1.convert(newModel1DTO));
		return new ResponseEntity<>(toDTO.convert(savedModel1), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<RacunDTO> edit(@Validated @RequestBody RacunDTO racunDTO, @PathVariable Long id) {
		if (!id.equals(racunDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Racun persisted = racunService.save(toModel1.convert(racunDTO));
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/prenos")
	public ResponseEntity<Void> interact(@RequestParam String brojRacuna1, @RequestParam String brojRacuna2,
			@RequestParam double iznos) {

		boolean izvrsena = racunService.interact(brojRacuna1, brojRacuna2, iznos);

		if (izvrsena) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}