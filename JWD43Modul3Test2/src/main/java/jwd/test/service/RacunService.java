package jwd.test.service;

import org.springframework.data.domain.Page;

import jwd.test.model.Racun;
import jwd.test.model.TipRacuna;

public interface RacunService {

	Racun findOne(Long id);

	Page<Racun> findAll(int pageNum, int rowsPerPage);

	Page<Racun> search(String jmbg, Long bankaId, int pageNum, int rowsPerPage);

	Racun save(Racun racun);

	Racun delete(Long id);

	boolean interact(String brojRacuna1, String brojRacuna2, double iznos);

}