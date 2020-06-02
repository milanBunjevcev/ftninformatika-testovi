package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Sprint;
import jwd.test.web.dto.SprintDTO;

@Component
public class SprintToSprintDTO implements Converter<Sprint, SprintDTO> {

	@Override
	public SprintDTO convert(Sprint source) {
		if (source == null) {
			return null;
		}

		SprintDTO dto = new SprintDTO();

		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setUkupnoBodova(source.getUkupnoBodova());

		return dto;
	}

	public List<SprintDTO> convert(List<Sprint> sourceList) {
		List<SprintDTO> dtoList = new ArrayList<>();

		for (Sprint temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}