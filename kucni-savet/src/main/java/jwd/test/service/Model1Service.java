package jwd.test.service;

import org.springframework.data.domain.Page;

import jwd.test.model.Model1;
import jwd.test.model.Model3;

public interface Model1Service {

	Model1 findOne(Long id);

	Page<Model1> findAll(int pageNum, int rowsPerPage);

	Page<Model1> search(String destinacija, Long prevoznikId, Double maksCena, int pageNum, int rowsPerPage);

	Model1 save(Model1 model1);

	Model1 delete(Long id);

	Model3 interact(Long model1Id);

}