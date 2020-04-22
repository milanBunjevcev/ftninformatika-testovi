package jwd.test.service;

import org.springframework.data.domain.Page;

import jwd.test.model.Poruka;
import jwd.test.model.Glas;

public interface PorukaService {

	Poruka findOne(Long id);

	Page<Poruka> findAll(int pageNum, int rowsPerPage);

	Page<Poruka> search(Long zgradaId, String naslov, String tip, int pageNum, int rowsPerPage);

	Poruka save(Poruka poruka);

	Poruka delete(Long id);

	Glas interact(Long porukaId, String predlogPodrzan);

}