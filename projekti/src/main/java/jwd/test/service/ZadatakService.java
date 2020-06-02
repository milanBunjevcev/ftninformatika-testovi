package jwd.test.service;

import org.springframework.data.domain.Page;

import jwd.test.model.Zadatak;
import jwd.test.model.Stanje;

public interface ZadatakService {

	Zadatak findOne(Long id);

	Page<Zadatak> findAll(int pageNum, int rowsPerPage);

	Page<Zadatak> search(String ime, Long sprintId, int pageNum, int rowsPerPage);

	Zadatak save(Zadatak zadatak);

	Zadatak delete(Long id);

	Stanje interact(Long zadatakId);

}