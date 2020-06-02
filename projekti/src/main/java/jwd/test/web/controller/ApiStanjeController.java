package jwd.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.test.model.Stanje;
import jwd.test.service.StanjeService;
import jwd.test.support.StanjeToStanjeDTO;
import jwd.test.web.dto.StanjeDTO;

@RestController
@RequestMapping(value = "/api/stanja")
public class ApiStanjeController {
	@Autowired
	private StanjeService stanjeService;

	@Autowired
	private StanjeToStanjeDTO toDto;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StanjeDTO>> get() {
		List<Stanje> stanja = stanjeService.findAll();
		return new ResponseEntity<List<StanjeDTO>>(toDto.convert(stanja), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
