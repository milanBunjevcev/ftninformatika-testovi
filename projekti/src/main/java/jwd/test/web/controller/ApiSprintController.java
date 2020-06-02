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

import jwd.test.model.Sprint;
import jwd.test.service.SprintService;
import jwd.test.support.SprintToSprintDTO;
import jwd.test.web.dto.SprintDTO;

@RestController
@RequestMapping(value = "/api/sprintovi")
public class ApiSprintController {

	@Autowired
	private SprintService sprintService;

	@Autowired
	private SprintToSprintDTO toDto;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SprintDTO>> get() {
		List<Sprint> model2List = sprintService.findAll();
		return new ResponseEntity<List<SprintDTO>>(toDto.convert(model2List), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<SprintDTO> getOne(@PathVariable Long id) {
		Sprint sprint = sprintService.findOne(id);
		if (sprint == null) {
			return new ResponseEntity<SprintDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SprintDTO>(toDto.convert(sprint), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}