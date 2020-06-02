package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Sprint;
import jwd.test.service.SprintService;
import jwd.test.web.dto.SprintDTO;

@Component
public class SprintDTOToSprint implements Converter<SprintDTO, Sprint> {

	@Autowired
	private SprintService sprintService;

	@Override
	public Sprint convert(SprintDTO dto) {
		Sprint sprint = null;

		if (dto.getId() == null) {
			sprint = new Sprint();
		} else {
			sprint = sprintService.findOne(dto.getId());
			if (sprint == null) {
				throw new IllegalStateException("Tried to modify a non-existant entity");
			}
		}

		sprint.setIme(dto.getIme());
		sprint.setUkupnoBodova(dto.getUkupnoBodova());

		return sprint;
	}

	public List<Sprint> convert(List<SprintDTO> dtoList) {
		List<Sprint> ret = new ArrayList<>();

		for (SprintDTO temp : dtoList) {
			ret.add(convert(temp));
		}

		return ret;
	}
}