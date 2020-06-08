package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Racun;
import jwd.test.model.TipRacuna;
import jwd.test.repository.TipRacunaRepository;
import jwd.test.model.Banka;
import jwd.test.service.RacunService;
import jwd.test.service.BankaService;
import jwd.test.web.dto.RacunDTO;

@Component
public class RacunDTOToRacun implements Converter<RacunDTO, Racun> {

	@Autowired
	private RacunService racunService;

	@Autowired
	private BankaService bankaService;

	@Autowired
	private TipRacunaRepository tipRacunaRepo;

	@Override
	public Racun convert(RacunDTO dto) {
		Banka banka = bankaService.findOne(dto.getBankaId());
		TipRacuna tipRacuna = tipRacunaRepo.findOne(dto.getTipRacunaId());

		if (banka != null && tipRacuna != null) {

			Racun racun = null;

			if (dto.getId() != null) {
				racun = racunService.findOne(dto.getId());
				racun.setStanjeRacuna(dto.getStanjeRacuna());
			} else {
				racun = new Racun();
				racun.setStanjeRacuna(0);
			}

			racun.setId(dto.getId());
			racun.setImePrezime(dto.getImePrezime());
			racun.setJmbg(dto.getJmbg());
			racun.setBrojRacuna(dto.getBrojRacuna());
			
			racun.setTipRacuna(tipRacuna);
			racun.setBanka(banka);

			return racun;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Racun> convert(List<RacunDTO> dtoList) {
		List<Racun> ret = new ArrayList<>();

		for (RacunDTO temp : dtoList) {
			ret.add(convert(temp));
		}

		return ret;
	}
}