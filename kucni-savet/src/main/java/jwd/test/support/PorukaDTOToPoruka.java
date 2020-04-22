package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Poruka;
import jwd.test.model.Zgrada;
import jwd.test.service.PorukaService;
import jwd.test.service.ZgradaService;
import jwd.test.web.dto.PorukaDTO;

@Component
public class PorukaDTOToPoruka implements Converter<PorukaDTO, Poruka> {

	@Autowired
	private PorukaService porukaService;

	@Autowired
	private ZgradaService zgradaService;

	@Override
	public Poruka convert(PorukaDTO dto) {
		Zgrada zgrada = zgradaService.findOne(dto.getZgradaId());

		if (zgrada != null) {

			Poruka poruka = null;

			if (dto.getId() != null) {
				poruka = porukaService.findOne(dto.getId());
			} else {
				poruka = new Poruka();
			}
			
			poruka.setId(dto.getId());
			poruka.setNaslov(dto.getNaslov());
			poruka.setTip(dto.getTip());
			poruka.setPotrebanProcenat(dto.getPotrebanProcenat());
			poruka.setPrihvacen(dto.isPrihvacen());
			poruka.setOpis(dto.getOpis());
			poruka.setZgrada(zgrada);

			return poruka;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Poruka> convert(List<PorukaDTO> dtoList) {
		List<Poruka> ret = new ArrayList<>();

		for (PorukaDTO temp : dtoList) {
			ret.add(convert(temp));
		}

		return ret;
	}
}