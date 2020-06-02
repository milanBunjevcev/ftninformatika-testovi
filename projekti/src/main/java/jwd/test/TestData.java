package jwd.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.test.model.Sprint;
import jwd.test.model.Stanje;
import jwd.test.service.SprintService;
import jwd.test.service.StanjeService;

@Component
public class TestData {

	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private StanjeService stanjeService;

	@PostConstruct
	public void init() {
		Sprint s1 = new Sprint();
		s1.setIme("Sprint 1");
		s1.setUkupnoBodova("0");
		
		sprintService.save(s1);
		
		Sprint s2 = new Sprint();
		s2.setIme("Sprint 2");
		s2.setUkupnoBodova("0");
		
		sprintService.save(s2);
		
		Stanje st1 = new Stanje();
		st1.setIme("nov");
		stanjeService.save(st1);
		
		Stanje st2 = new Stanje();
		st2.setIme("u-toku");
		stanjeService.save(st2);
		
		Stanje st3 = new Stanje();
		st3.setIme("zavrsen");
		stanjeService.save(st3);
		
	}

}
