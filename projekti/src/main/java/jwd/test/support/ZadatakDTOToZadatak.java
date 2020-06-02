package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Sprint;
import jwd.test.model.Stanje;
import jwd.test.model.Zadatak;
import jwd.test.repository.StanjeRepository;
import jwd.test.service.SprintService;
import jwd.test.service.ZadatakService;
import jwd.test.web.dto.ZadatakDTO;

@Component
public class ZadatakDTOToZadatak implements Converter<ZadatakDTO, Zadatak> {

	@Autowired
	private ZadatakService zadatakService;

	@Autowired
	private SprintService sprintService;

	@Autowired
	private StanjeRepository stanjeRepository;

	@Override
	public Zadatak convert(ZadatakDTO dto) {
		System.out.println(dto.getSprintId());
		Sprint sprint = sprintService.findOne(dto.getSprintId());
		Stanje stanje = stanjeRepository.findOne(dto.getStanjeId());

		if (sprint != null && stanje != null) {

			Zadatak zadatak = null;

			if (dto.getId() != null) {
				zadatak = zadatakService.findOne(dto.getId());
				zadatak.setStanje(stanje);
			} else {
				zadatak = new Zadatak();
				zadatak.setStanje(stanjeRepository.findOne(1L));
			}

			zadatak.setId(dto.getId());
			zadatak.setIme(dto.getIme());
			zadatak.setZaduzeni(dto.getZaduzeni());
			zadatak.setBodovi(dto.getBodovi());
			zadatak.setSprint(sprint);
			// zadatak.setStanje(stanje);

			return zadatak;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Zadatak> convert(List<ZadatakDTO> dtoList) {
		List<Zadatak> ret = new ArrayList<>();

		for (ZadatakDTO temp : dtoList) {
			ret.add(convert(temp));
		}

		return ret;
	}
}