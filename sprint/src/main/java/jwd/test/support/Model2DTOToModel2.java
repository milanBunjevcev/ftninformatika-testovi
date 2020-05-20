package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Model2;
import jwd.test.service.Model2Service;
import jwd.test.web.dto.Model2DTO;

@Component
public class Model2DTOToModel2 implements Converter<Model2DTO, Model2> {

	@Autowired
	private Model2Service model2Service;

	@Override
	public Model2 convert(Model2DTO dto) {
		Model2 model2 = null;

		if (dto.getId() == null) {
			model2 = new Model2();
		} else {
			model2 = model2Service.findOne(dto.getId());
			if (model2 == null) {
				throw new IllegalStateException("Tried to modify a non-existant entity");
			}
		}

		// TODO set Model2 polja

		return model2;
	}

	public List<Model2> convert(List<Model2DTO> dtoList) {
		List<Model2> ret = new ArrayList<>();

		for (Model2DTO temp : dtoList) {
			ret.add(convert(temp));
		}

		return ret;
	}
}