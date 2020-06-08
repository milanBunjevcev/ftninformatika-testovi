package jwd.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.test.model.Banka;
import jwd.test.model.TipRacuna;
import jwd.test.repository.TipRacunaRepository;
import jwd.test.service.BankaService;

@Component
public class TestData {

	@Autowired
	private BankaService bankaService;
	
	@Autowired
	private TipRacunaRepository tipRacunaRepo;

	@PostConstruct
	public void init() {

		Banka b1 = new Banka();
		b1.setNaziv("prva banka");
		b1.setSredstva(0);
		bankaService.save(b1);
		
		Banka b2 = new Banka();
		b2.setNaziv("druga banka");
		b2.setSredstva(0);
		bankaService.save(b2);
		
		TipRacuna tr1 = new TipRacuna();
		tr1.setNaziv("tekuci 1");
		tr1.setProvizija(10);
		tr1.setBanka(b1);
		tipRacunaRepo.save(tr1);
		
		TipRacuna tr2 = new TipRacuna();
		tr2.setNaziv("namenski 1");
		tr2.setProvizija(5);
		tr2.setBanka(b1);
		tipRacunaRepo.save(tr2);
		
		TipRacuna tr3 = new TipRacuna();
		tr3.setNaziv("tekuci 2");
		tr3.setProvizija(15);
		tr3.setBanka(b2);
		tipRacunaRepo.save(tr3);
		
		TipRacuna tr4 = new TipRacuna();
		tr4.setNaziv("namenski 2");
		tr4.setProvizija(20);
		tr4.setBanka(b2);
		tipRacunaRepo.save(tr4);
	}

}
