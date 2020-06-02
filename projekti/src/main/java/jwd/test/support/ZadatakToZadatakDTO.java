package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Zadatak;
import jwd.test.web.dto.ZadatakDTO;

@Component
public class ZadatakToZadatakDTO implements Converter<Zadatak, ZadatakDTO> {

	@Override
	public ZadatakDTO convert(Zadatak source) {
		if (source == null) {
			return null;
		}

		ZadatakDTO dto = new ZadatakDTO();

		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setZaduzeni(source.getZaduzeni());
		dto.setBodovi(source.getBodovi());
		dto.setSprintId(source.getSprint().getId());
		dto.setSprintIme(source.getSprint().getIme());
		dto.setStanjeId(source.getStanje().getId());
		dto.setStanjeIme(source.getStanje().getIme());

		return dto;
	}

	public List<ZadatakDTO> convert(List<Zadatak> sourceList) {
		List<ZadatakDTO> dtoList = new ArrayList<>();

		for (Zadatak temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}