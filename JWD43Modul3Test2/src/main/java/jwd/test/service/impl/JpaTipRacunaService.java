package jwd.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.test.model.TipRacuna;
import jwd.test.repository.TipRacunaRepository;
import jwd.test.service.TipRacunaService;

@Service
public class JpaTipRacunaService implements TipRacunaService {

	@Autowired
	private TipRacunaRepository tipRacunaRepo;

	@Override
	public List<TipRacuna> findByBankaId(Long id) {
		return tipRacunaRepo.findByBankaId(id);
	}

}
