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

import jwd.test.model.Model1;
import jwd.test.model.Model3;
import jwd.test.service.Model1Service;
import jwd.test.support.Model1DTOToModel1;
import jwd.test.support.Model1ToModel1DTO;
import jwd.test.support.Model3ToModel3DTO;
import jwd.test.web.dto.Model1DTO;
import jwd.test.web.dto.Model3DTO;

@RestController
@RequestMapping(value = "/api/")
public class ApiModel1Controller {
	@Autowired
	private Model1Service model1Service;

	@Autowired
	private Model1ToModel1DTO toDTO;
	@Autowired
	private Model1DTOToModel1 toModel1;

	@Autowired
	private Model3ToModel3DTO toModel3DTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Model1DTO>> get(@RequestParam(required = false) String destinacija,
			@RequestParam(required = false) Long prevoznikId, @RequestParam(required = false) Double maksCena,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "rowsPerPage", defaultValue = "5") int rowsPerPage) {

		Page<Model1> linijePage = null;

		if (destinacija != null || prevoznikId != null || maksCena != null) {
			linijePage = model1Service.search(destinacija, prevoznikId, maksCena, pageNum, rowsPerPage);
		} else {
			linijePage = model1Service.findAll(pageNum, rowsPerPage);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linijePage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(linijePage.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Model1DTO> getOne(@PathVariable Long id) {
		Model1 model1 = model1Service.findOne(id);
		if (model1 == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(model1), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Model1DTO> delete(@PathVariable Long id) {
		Model1 deleted = model1Service.delete(id);
		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Model1DTO> add(@Validated @RequestBody Model1DTO newModel1DTO) {
		Model1 savedModel1 = model1Service.save(toModel1.convert(newModel1DTO));
		return new ResponseEntity<>(toDTO.convert(savedModel1), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<Model1DTO> edit(@Validated @RequestBody Model1DTO model1DTO, @PathVariable Long id) {
		if (!id.equals(model1DTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Model1 persisted = model1Service.save(toModel1.convert(model1DTO));
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ResponseEntity<Model3DTO> interact(@PathVariable Long id) {
		Model3 model3 = model1Service.interact(id);
		// TODO prilagoditi
		if (model3 != null) {
			return new ResponseEntity<Model3DTO>(toModel3DTO.convert(model3), HttpStatus.OK);
		}

		return new ResponseEntity<Model3DTO>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}