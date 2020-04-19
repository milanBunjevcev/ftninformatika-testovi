package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Model2;
import jwd.test.web.dto.Model2DTO;

@Component
public class Model2ToModel2DTO implements Converter<Model2, Model2DTO> {

	@Override
	public Model2DTO convert(Model2 source) {
		if (source == null) {
			return null;
		}

		Model2DTO dto = new Model2DTO();

		dto.setId(source.getId());
		// TODO set Model2DTO polja

		return dto;
	}

	public List<Model2DTO> convert(List<Model2> sourceList) {
		List<Model2DTO> dtoList = new ArrayList<>();

		for (Model2 temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}