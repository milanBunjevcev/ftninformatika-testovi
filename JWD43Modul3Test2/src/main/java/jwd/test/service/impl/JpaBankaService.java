package jwd.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.test.model.Banka;
import jwd.test.repository.BankaRepository;
import jwd.test.service.BankaService;

@Service
public class JpaBankaService implements BankaService {

	@Autowired
	private BankaRepository bankaRepository;
	
	@Override
	public Banka findOne(Long id) {
		return bankaRepository.findOne(id);
	}

	@Override
	public List<Banka> findAll() {
		return bankaRepository.findAll();
	}
	
	@Override
	public Banka save(Banka banka) {
		return bankaRepository.save(banka);
	}

	@Override
	public Banka delete(Long id) {
		Banka banka = bankaRepository.findOne(id);
		if(banka != null){
			bankaRepository.delete(banka);
		}
		
		return banka;
	}	
}