package jwd.test.service;

import java.util.List;

import jwd.test.model.Stanje;

public interface StanjeService {
	List<Stanje> findAll();

	Stanje save(Stanje stanje);
	
	Stanje findOne(Long id);
}
