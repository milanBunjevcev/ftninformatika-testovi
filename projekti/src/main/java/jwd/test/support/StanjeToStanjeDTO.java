package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Stanje;
import jwd.test.web.dto.StanjeDTO;

@Component
public class StanjeToStanjeDTO implements Converter<Stanje, StanjeDTO> {

	@Override
	public StanjeDTO convert(Stanje source) {
		if (source == null) {
			return null;
		}

		StanjeDTO dto = new StanjeDTO();

		dto.setId(source.getId());
		dto.setIme(source.getIme());

		return dto;
	}

	public List<StanjeDTO> convert(List<Stanje> sourceList) {
		List<StanjeDTO> dtoList = new ArrayList<>();

		for (Stanje temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}