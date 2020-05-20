package jwd.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.test.model.Model2;
import jwd.test.repository.Model2Repository;
import jwd.test.service.Model2Service;

@Service
public class JpaModel2Service implements Model2Service {

	@Autowired
	private Model2Repository model2Repository;
	
	@Override
	public Model2 findOne(Long id) {
		return model2Repository.findOne(id);
	}

	@Override
	public List<Model2> findAll() {
		return model2Repository.findAll();
	}
	
	@Override
	public Model2 save(Model2 model2) {
		return model2Repository.save(model2);
	}

	@Override
	public Model2 delete(Long id) {
		Model2 model2 = model2Repository.findOne(id);
		if(model2 != null){
			model2Repository.delete(model2);
		}
		
		return model2;
	}	
}