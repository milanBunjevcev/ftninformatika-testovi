package jwd.test.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.test.model.Poruka;
import jwd.test.model.Glas;
import jwd.test.repository.PorukaRepository;
import jwd.test.repository.GlasRepository;
import jwd.test.service.PorukaService;

@Service
@Transactional
public class JpaPorukaService implements PorukaService {

	@Autowired
	private PorukaRepository porukaRepository;

	@Autowired
	private GlasRepository glasRepository;

	@Override
	public Poruka findOne(Long id) {
		return porukaRepository.findOne(id);
	}

	@Override
	public Page<Poruka> findAll(int pageNum, int rowsPerPage) {
		return porukaRepository.findAll(new PageRequest(pageNum, rowsPerPage));
	}

	@Override
	public Page<Poruka> search(Long zgradaId, String naslov, String tip, int pageNum, int rowsPerPage) {

		if (naslov != null) {
			naslov = '%' + naslov + '%';
		}
		if (tip != null) {
			tip = '%' + tip + '%';
		}

		return porukaRepository.search(zgradaId, naslov, tip, new PageRequest(pageNum, rowsPerPage));
	}

	@Override
	public Poruka save(Poruka poruka) {
		return porukaRepository.save(poruka);
	}

	@Override
	public Poruka delete(Long id) {
		Poruka poruka = porukaRepository.findOne(id);
		if (poruka != null) {
			porukaRepository.delete(poruka);
		}

		return poruka;
	}

	@Override
	public Glas interact(Long porukaId, String predlogPodrzan) {
		Poruka poruka = findOne(porukaId);

		if (poruka != null) {

			int brojStanara = poruka.getZgrada().getBrojStanara();
			if (brojStanara == poruka.getGlasovi().size()) {
				return null;
			}

			Glas glas = new Glas();
			glas.setPoruka(poruka);
			glas.setPredlogPodrzan(predlogPodrzan);

			int brojGlasovaZa = poruka.getZaGlasovi();
			if (brojGlasovaZa * 100 / brojStanara >= poruka.getPotrebanProcenat()) {
				poruka.setPrihvacen(true);
			}

			return glas;
		} else {
			throw new IllegalArgumentException("");
		}
	}
}