package jwd.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.test.model.Zgrada;
import jwd.test.service.ZgradaService;
import jwd.test.support.PorukaToPorukaDTO;
import jwd.test.support.ZgradaToZgradaDTO;
import jwd.test.web.dto.PorukaDTO;
import jwd.test.web.dto.ZgradaDTO;

@RestController
@RequestMapping(value = "/api/zgrade")
public class ApiZgradaController {

	@Autowired
	private ZgradaService zgradaService;

	@Autowired
	private ZgradaToZgradaDTO toDto;

	@Autowired
	private PorukaToPorukaDTO toPorukaDto;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ZgradaDTO>> get() {
		List<Zgrada> model2List = zgradaService.findAll();
		return new ResponseEntity<List<ZgradaDTO>>(toDto.convert(model2List), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<ZgradaDTO> getOne(@PathVariable Long id) {
		Zgrada zgrada = zgradaService.findOne(id);
		if (zgrada == null) {
			return new ResponseEntity<ZgradaDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ZgradaDTO>(toDto.convert(zgrada), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/poruke")
	public ResponseEntity<List<PorukaDTO>> getPorukeForIdZgrade(@PathVariable Long id) {
		Zgrada zgrada = zgradaService.findOne(id);
		if (zgrada == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toPorukaDto.convert(zgrada.getPoruke()), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}