package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.TipRacuna;
import jwd.test.web.dto.TipRacunaDTO;

@Component
public class TipRacunToTipRacunaDTO implements Converter<TipRacuna, TipRacunaDTO> {

	@Override
	public TipRacunaDTO convert(TipRacuna source) {
		if (source == null) {
			return null;
		}

		TipRacunaDTO dto = new TipRacunaDTO();

		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setProvizija(source.getProvizija());
		
		dto.setBankaId(source.getBanka().getId());

		return dto;
	}

	public List<TipRacunaDTO> convert(List<TipRacuna> sourceList) {
		List<TipRacunaDTO> dtoList = new ArrayList<>();

		for (TipRacuna temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}