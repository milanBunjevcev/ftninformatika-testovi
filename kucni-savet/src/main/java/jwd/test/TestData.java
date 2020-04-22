package jwd.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.test.model.Poruka;
import jwd.test.model.Zgrada;
import jwd.test.service.PorukaService;
import jwd.test.service.ZgradaService;

@Component
public class TestData {

	@Autowired
	private ZgradaService zgradaService;

	@Autowired
	private PorukaService porukaService;

	@PostConstruct
	public void init() {

		Zgrada zgrada1 = new Zgrada();
		zgrada1.setAdresa("adresa 1");
		zgrada1.setPredsednikKS("pera");
		zgrada1.setBrojStanova(10);
		zgrada1.setBrojStanara(10);

		zgradaService.save(zgrada1);

		Zgrada zgrada2 = new Zgrada();
		zgrada2.setAdresa("adresa 2");
		zgrada2.setPredsednikKS("mica");
		zgrada2.setBrojStanova(4);
		zgrada2.setBrojStanara(4);

		zgradaService.save(zgrada2);

		//

		Poruka p1 = new Poruka();
		p1.setNaslov("nestanak vode");
		p1.setTip("obaveštenje");
		p1.setPotrebanProcenat(0);
		p1.setOpis("oko podneva");
		p1.setZgrada(zgrada1);

		porukaService.save(p1);
		
		Poruka p2 = new Poruka();
		p2.setNaslov("Obnova fasade");
		p2.setTip("predlog");
		p2.setPotrebanProcenat(10);
		p2.setOpis("Da li ste za?");
		p2.setZgrada(zgrada2);

		porukaService.save(p2);

	}

}
