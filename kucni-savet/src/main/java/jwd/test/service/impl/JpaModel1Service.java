package jwd.test.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.test.model.Model1;
import jwd.test.model.Model3;
import jwd.test.repository.Model1Repository;
import jwd.test.repository.Model3Repository;
import jwd.test.service.Model1Service;

@Service
@Transactional
public class JpaModel1Service implements Model1Service {

	@Autowired
	private Model1Repository model1Repository;

	@Autowired
	private Model3Repository model3Repository;

	@Override
	public Model1 findOne(Long id) {
		return model1Repository.findOne(id);
	}

	@Override
	public Page<Model1> findAll(int pageNum, int rowsPerPage) {
		return model1Repository.findAll(new PageRequest(pageNum, rowsPerPage));
	}

	@Override
	public Page<Model1> search(String destinacija, Long prevoznikId, Double maksCena, int pageNum, int rowsPerPage) {

		if (destinacija != null) {
			destinacija = '%' + destinacija + '%';
		}

		return model1Repository.search(destinacija, prevoznikId, maksCena, new PageRequest(pageNum, rowsPerPage));
	}

	@Override
	public Model1 save(Model1 model1) {
		return model1Repository.save(model1);
	}

	@Override
	public Model1 delete(Long id) {
		Model1 model1 = model1Repository.findOne(id);
		if (model1 != null) {
			model1Repository.delete(model1);
		}

		return model1;
	}

	@Override
	public Model3 interact(Long model1Id) {
		Model1 model1 = findOne(model1Id);

		if (model1 != null) {
			Model3 model3 = null;
			if (true) {

			}

			return model3;
		} else {
			throw new IllegalArgumentException("");
		}
	}
}