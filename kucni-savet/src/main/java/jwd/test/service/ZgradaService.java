package jwd.test.service;

import java.util.List;

import jwd.test.model.Zgrada;

public interface ZgradaService {
	
	Zgrada findOne(Long id);

	List<Zgrada> findAll();

	Zgrada save(Zgrada zgrada);

	Zgrada delete(Long id);
	
}