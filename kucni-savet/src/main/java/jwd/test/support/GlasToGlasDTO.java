package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Glas;
import jwd.test.web.dto.GlasDTO;

@Component
public class GlasToGlasDTO implements Converter<Glas, GlasDTO> {

	@Override
	public GlasDTO convert(Glas source) {
		if (source == null) {
			return null;
		}

		GlasDTO dto = new GlasDTO();

		dto.setId(source.getId());
		dto.setPorukaId(source.getPoruka().getId());
		dto.setPorukaNaslov(source.getPoruka().getNaslov());
		dto.setPredlogPodrzan(source.getPredlogPodrzan());

		return dto;
	}

	public List<GlasDTO> convert(List<Glas> sourceList) {
		List<GlasDTO> dtoList = new ArrayList<>();

		for (Glas temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}