package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Banka;
import jwd.test.service.BankaService;
import jwd.test.web.dto.BankaDTO;

@Component
public class BankaDTOToBanka implements Converter<BankaDTO, Banka> {

	@Autowired
	private BankaService bankaService;

	@Override
	public Banka convert(BankaDTO dto) {
		Banka banka = null;

		if (dto.getId() == null) {
			banka = new Banka();
		} else {
			banka = bankaService.findOne(dto.getId());
			if (banka == null) {
				throw new IllegalStateException("Tried to modify a non-existant entity");
			}
		}

		banka.setNaziv(dto.getNaziv());
		banka.setSredstva(dto.getSredstva());

		return banka;
	}

	public List<Banka> convert(List<BankaDTO> dtoList) {
		List<Banka> ret = new ArrayList<>();

		for (BankaDTO temp : dtoList) {
			ret.add(convert(temp));
		}

		return ret;
	}
}