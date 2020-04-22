package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Poruka;
import jwd.test.web.dto.PorukaDTO;

@Component
public class PorukaToPorukaDTO implements Converter<Poruka, PorukaDTO> {

	@Override
	public PorukaDTO convert(Poruka source) {
		if (source == null) {
			return null;
		}

		PorukaDTO dto = new PorukaDTO();

		dto.setId(source.getId());
		dto.setNaslov(source.getNaslov());
		dto.setTip(source.getTip());
		dto.setPotrebanProcenat(source.getPotrebanProcenat());
		dto.setPrihvacen(source.isPrihvacen());
		dto.setOpis(source.getOpis());
		dto.setZgradaId(source.getZgrada().getId());
		dto.setZgradaAdresa(source.getZgrada().getAdresa());
		dto.setMozeGlasati(false);
		if (source.getZgrada().getBrojStanara() > source.getGlasovi().size()) {
			dto.setMozeGlasati(true);
		}

		return dto;
	}

	public List<PorukaDTO> convert(List<Poruka> sourceList) {
		List<PorukaDTO> dtoList = new ArrayList<>();

		for (Poruka temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}