package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Banka;
import jwd.test.web.dto.BankaDTO;

@Component
public class BankaToBankaDTO implements Converter<Banka, BankaDTO> {

	@Override
	public BankaDTO convert(Banka source) {
		if (source == null) {
			return null;
		}

		BankaDTO dto = new BankaDTO();

		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setSredstva(source.getSredstva());

		return dto;
	}

	public List<BankaDTO> convert(List<Banka> sourceList) {
		List<BankaDTO> dtoList = new ArrayList<>();

		for (Banka temp : sourceList) {
			dtoList.add(convert(temp));
		}

		return dtoList;
	}

}