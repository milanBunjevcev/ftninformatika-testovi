package jwd.test.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.test.model.Zadatak;
import jwd.test.model.Stanje;
import jwd.test.repository.ZadatakRepository;
import jwd.test.repository.StanjeRepository;
import jwd.test.service.ZadatakService;

@Service
@Transactional
public class JpaZadatakService implements ZadatakService {

	@Autowired
	private ZadatakRepository zadatakRepository;

	@Autowired
	private StanjeRepository stanjeRepository;

	@Override
	public Zadatak findOne(Long id) {
		return zadatakRepository.findOne(id);
	}

	@Override
	public Page<Zadatak> findAll(int pageNum, int rowsPerPage) {
		return zadatakRepository.findAll(new PageRequest(pageNum, rowsPerPage));
	}

	@Override
	public Page<Zadatak> search(String ime, Long sprintId, int pageNum, int rowsPerPage) {

		if (ime != null) {
			ime = '%' + ime + '%';
		}

		return zadatakRepository.search(ime, sprintId, new PageRequest(pageNum, rowsPerPage));
	}

	@Override
	public Zadatak save(Zadatak zadatak) {
		Zadatak zad = zadatakRepository.save(zadatak);
		if (zad != null) {
			zadatak.getSprint().racunajBodove();
		}
		return zad;
	}

	@Override
	public Zadatak delete(Long id) {
		Zadatak zadatak = zadatakRepository.findOne(id);
		if (zadatak != null) {
			int bodoviUkupno = Integer.parseInt(zadatak.getSprint().getUkupnoBodova());
			bodoviUkupno -= zadatak.getBodovi();
			zadatak.getSprint().setUkupnoBodova("" + bodoviUkupno);
			zadatakRepository.delete(zadatak);
		}
		return zadatak;
	}

	@Override
	public Stanje interact(Long model1Id) {
		Zadatak zadatak = findOne(model1Id);

		if (zadatak != null) {
			if (zadatak.getStanje().getId() < 3L) {
				Stanje stanje = null;
				stanje = stanjeRepository.findOne(zadatak.getStanje().getId() + 1L);
				if (stanje != null) {
					zadatak.setStanje(stanje);
				}
			}

			return zadatak.getStanje();
		} else {
			throw new IllegalArgumentException("");
		}
	}
}