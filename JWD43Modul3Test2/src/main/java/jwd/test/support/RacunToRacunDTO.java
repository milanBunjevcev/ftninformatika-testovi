package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Racun;
import jwd.test.web.dto.RacunDTO;

@Component
public class RacunToRacunDTO implements Converter<Racun, RacunDTO> {

	@Override
	public RacunDTO convert(Racun source) {
		if (source == null) {
			return null;
		}

		RacunDTO dto = new RacunDTO();

		dto.setId(source.getId());
		dto.setImePrezime(source.getImePrezime());
		dto.setJmbg(source.getJmbg());
		dto.setBrojRacuna(source.getBrojRacuna());
		dto.setStanjeRacuna(source.getStanjeRacuna());

		dto.setTipRacunaId(source.getTipRacuna().getId());
		dto.setTipRacunaNaziv(source.getTipRacuna().getNaziv());

		dto.setBankaId(source.getBanka().getId());
		dto.setBankaNaziv(source.getBanka().getNaziv());

		return dto;
	}

	public List<RacunDTO> convert(List<Racun> sourceList) {
		List<RacunDTO> dtoList = new ArrayList<>();

		for (Racun temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}