package jwd.test.service;

import java.util.List;

import jwd.test.model.TipRacuna;

public interface TipRacunaService {
	List<TipRacuna> findByBankaId(Long id);
}
