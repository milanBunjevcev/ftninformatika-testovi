package jwd.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.test.model.Model3;

@Repository
public interface Model3Repository extends JpaRepository<Model3, Long> {
		
}