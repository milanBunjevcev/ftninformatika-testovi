package jwd.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.test.model.Stanje;
import jwd.test.repository.StanjeRepository;
import jwd.test.service.StanjeService;

@Service
public class JpaStanjeService implements StanjeService {

	@Autowired
	private StanjeRepository stanjeRepository;

	@Override
	public List<Stanje> findAll() {
		return stanjeRepository.findAll();
	}

	@Override
	public Stanje save(Stanje stanje) {
		return stanjeRepository.save(stanje);
	}

	@Override
	public Stanje findOne(Long id) {
		return stanjeRepository.findOne(id);
	}

}
