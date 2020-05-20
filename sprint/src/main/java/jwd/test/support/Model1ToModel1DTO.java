package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Model1;
import jwd.test.web.dto.Model1DTO;

@Component
public class Model1ToModel1DTO implements Converter<Model1, Model1DTO> {

	@Override
	public Model1DTO convert(Model1 source) {
		if (source == null) {
			return null;
		}

		Model1DTO dto = new Model1DTO();

		dto.setId(source.getId());
		// TODO set Model1DTO polja

		return dto;
	}

	public List<Model1DTO> convert(List<Model1> sourceList) {
		List<Model1DTO> dtoList = new ArrayList<>();

		for (Model1 temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}