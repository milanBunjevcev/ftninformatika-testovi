package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Model3;
import jwd.test.web.dto.Model3DTO;

@Component
public class Model3ToModel3DTO implements Converter<Model3, Model3DTO> {

	@Override
	public Model3DTO convert(Model3 source) {
		if (source == null) {
			return null;
		}

		Model3DTO dto = new Model3DTO();

		dto.setId(source.getId());
		// TODO set Model3DTO polja

		return dto;
	}

	public List<Model3DTO> convert(List<Model3> sourceList) {
		List<Model3DTO> dtoList = new ArrayList<>();

		for (Model3 temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}