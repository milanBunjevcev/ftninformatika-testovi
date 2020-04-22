package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Zgrada;
import jwd.test.web.dto.ZgradaDTO;

@Component
public class ZgradaToZgradaDTO implements Converter<Zgrada, ZgradaDTO> {

	@Override
	public ZgradaDTO convert(Zgrada source) {
		if (source == null) {
			return null;
		}

		ZgradaDTO dto = new ZgradaDTO();

		dto.setId(source.getId());
		dto.setAdresa(source.getAdresa());
		dto.setPredsednikKS(source.getPredsednikKS());
		dto.setBrojStanova(source.getBrojStanova());
		dto.setBrojStanara(source.getBrojStanara());

		return dto;
	}

	public List<ZgradaDTO> convert(List<Zgrada> sourceList) {
		List<ZgradaDTO> dtoList = new ArrayList<>();

		for (Zgrada temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}