package jwd.test.service;

import java.util.List;

import jwd.test.model.Sprint;

public interface SprintService {
	
	Sprint findOne(Long id);

	List<Sprint> findAll();

	Sprint save(Sprint sprint);

	Sprint delete(Long id);
	
}