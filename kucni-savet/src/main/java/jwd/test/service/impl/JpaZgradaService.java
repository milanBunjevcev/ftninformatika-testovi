package jwd.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.test.model.Zgrada;
import jwd.test.repository.ZgradaRepository;
import jwd.test.service.ZgradaService;

@Service
public class JpaZgradaService implements ZgradaService {

	@Autowired
	private ZgradaRepository zgradaRepository;
	
	@Override
	public Zgrada findOne(Long id) {
		return zgradaRepository.findOne(id);
	}

	@Override
	public List<Zgrada> findAll() {
		return zgradaRepository.findAll();
	}
	
	@Override
	public Zgrada save(Zgrada zgrada) {
		return zgradaRepository.save(zgrada);
	}

	@Override
	public Zgrada delete(Long id) {
		Zgrada zgrada = zgradaRepository.findOne(id);
		if(zgrada != null){
			zgradaRepository.delete(zgrada);
		}
		
		return zgrada;
	}	
}