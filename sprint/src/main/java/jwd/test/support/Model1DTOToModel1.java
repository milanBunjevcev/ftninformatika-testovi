package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Model1;
import jwd.test.model.Model2;
import jwd.test.service.Model1Service;
import jwd.test.service.Model2Service;
import jwd.test.web.dto.Model1DTO;

@Component
public class Model1DTOToModel1 implements Converter<Model1DTO, Model1> {

	@Autowired
	private Model1Service model1Service;

	@Autowired
	private Model2Service model2Service;

	@Override
	public Model1 convert(Model1DTO dto) {
		Model2 model2 = model2Service.findOne(dto.getModel2Id());

		if (model2 != null) {

			Model1 model1 = null;

			if (dto.getId() != null) {
				model1 = model1Service.findOne(dto.getId());
			} else {
				model1 = new Model1();
			}
			
			model1.setId(dto.getId());
			//TODO set Model1 polja

			return model1;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Model1> convert(List<Model1DTO> dtoList) {
		List<Model1> ret = new ArrayList<>();

		for (Model1DTO temp : dtoList) {
			ret.add(convert(temp));
		}

		return ret;
	}
}