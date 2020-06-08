package jwd.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.test.model.Racun;
import jwd.test.model.TipRacuna;
import jwd.test.repository.RacunRepository;
import jwd.test.repository.TipRacunaRepository;
import jwd.test.service.RacunService;

@Service
@Transactional
public class JpaRacunService implements RacunService {

	@Autowired
	private RacunRepository racunRepository;

	@Autowired
	private TipRacunaRepository tipRacunaRepository;

	@Override
	public Racun findOne(Long id) {
		return racunRepository.findOne(id);
	}

	@Override
	public Page<Racun> findAll(int pageNum, int rowsPerPage) {
		return racunRepository.findAll(new PageRequest(pageNum, rowsPerPage));
	}

	@Override
	public Page<Racun> search(String jmbg, Long bankaId, int pageNum, int rowsPerPage) {

		if (jmbg != null) {
			jmbg = '%' + jmbg + '%';
		}

		return racunRepository.search(jmbg, bankaId, new PageRequest(pageNum, rowsPerPage));
	}

	@Override
	public Racun save(Racun racun) {
		return racunRepository.save(racun);
	}

	@Override
	public Racun delete(Long id) {
		Racun racun = racunRepository.findOne(id);
		if (racun != null) {
			racunRepository.delete(racun);
		}

		return racun;
	}

	@Override
	public boolean interact(String brojRacuna1, String brojRacuna2, double iznos) {
		Racun racun1 = null;
		Racun racun2 = null;

		List<Racun> temp1 = racunRepository.findByBrojRacuna(brojRacuna1);
		if (temp1.size() > 0) {
			racun1 = temp1.get(0);
			System.out.println(racun1.getId());
		}

		List<Racun> temp2 = racunRepository.findByBrojRacuna(brojRacuna2);
		if (temp1.size() > 0) {
			racun2 = temp2.get(0);
			System.out.println(racun2.getId());
		}

		if (racun1 != null && racun2 != null) {
			if (racun1.getStanjeRacuna() >= iznos) {
				racun1.setStanjeRacuna(racun1.getStanjeRacuna() - iznos);
				double provizija = iznos * racun1.getTipRacuna().getProvizija() / 100;
				if (provizija > 1000) {
					provizija = 1000;
				}
				racun2.setStanjeRacuna(racun2.getStanjeRacuna() + iznos - provizija);
				racun1.getBanka().setSredstva(racun1.getBanka().getSredstva() + provizija);
			} else {
				return false;
			}

			return true;
		} else {
			return false;
		}
	}
}