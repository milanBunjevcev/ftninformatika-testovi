package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Zgrada;
import jwd.test.service.ZgradaService;
import jwd.test.web.dto.ZgradaDTO;

@Component
public class ZgradaDTOToZgrada implements Converter<ZgradaDTO, Zgrada> {

	@Autowired
	private ZgradaService zgradaService;

	@Override
	public Zgrada convert(ZgradaDTO dto) {
		Zgrada zgrada = null;

		if (dto.getId() == null) {
			zgrada = new Zgrada();
		} else {
			zgrada = zgradaService.findOne(dto.getId());
			if (zgrada == null) {
				throw new IllegalStateException("Tried to modify a non-existant entity");
			}
		}

		zgrada.setAdresa(dto.getAdresa());
		zgrada.setPredsednikKS(dto.getPredsednikKS());
		zgrada.setBrojStanova(dto.getBrojStanova());
		zgrada.setBrojStanara(dto.getBrojStanara());

		return zgrada;
	}

	public List<Zgrada> convert(List<ZgradaDTO> dtoList) {
		List<Zgrada> ret = new ArrayList<>();

		for (ZgradaDTO temp : dtoList) {
			ret.add(convert(temp));
		}

		return ret;
	}
}