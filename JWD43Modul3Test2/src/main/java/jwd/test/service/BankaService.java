package jwd.test.service;

import java.util.List;

import jwd.test.model.Banka;

public interface BankaService {
	
	Banka findOne(Long id);

	List<Banka> findAll();

	Banka save(Banka banka);

	Banka delete(Long id);
	
}