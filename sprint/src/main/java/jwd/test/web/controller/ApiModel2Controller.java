package jwd.test.web.controller;

import java.util.ArrayList;
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

import jwd.test.model.Model2;
import jwd.test.service.Model2Service;
import jwd.test.support.Model2ToModel2DTO;
import jwd.test.web.dto.Model2DTO;

@RestController
@RequestMapping(value = "/api/")
public class ApiModel2Controller {

	@Autowired
	private Model2Service model2Service;

	@Autowired
	private Model2ToModel2DTO toDto;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Model2DTO>> get() {
		List<Model2> model2List = model2Service.findAll();
		return new ResponseEntity<List<Model2DTO>>(toDto.convert(model2List), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Model2DTO> getOne(@PathVariable Long id) {
		Model2 model2 = model2Service.findOne(id);
		if (model2 == null) {
			return new ResponseEntity<Model2DTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Model2DTO>(toDto.convert(model2), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}