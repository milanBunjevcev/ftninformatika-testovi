package jwd.test.service;

import java.util.List;

import jwd.test.model.Model2;

public interface Model2Service {
	
	Model2 findOne(Long id);

	List<Model2> findAll();

	Model2 save(Model2 model2);

	Model2 delete(Long id);
	
}