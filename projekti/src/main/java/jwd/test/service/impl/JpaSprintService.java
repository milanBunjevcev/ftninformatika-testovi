package jwd.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.test.model.Sprint;
import jwd.test.repository.SprintRepository;
import jwd.test.service.SprintService;

@Service
public class JpaSprintService implements SprintService {

	@Autowired
	private SprintRepository sprintRepository;
	
	@Override
	public Sprint findOne(Long id) {
		return sprintRepository.findOne(id);
	}

	@Override
	public List<Sprint> findAll() {
		return sprintRepository.findAll();
	}
	
	@Override
	public Sprint save(Sprint sprint) {
		return sprintRepository.save(sprint);
	}

	@Override
	public Sprint delete(Long id) {
		Sprint sprint = sprintRepository.findOne(id);
		if(sprint != null){
			sprintRepository.delete(sprint);
		}
		
		return sprint;
	}	
}