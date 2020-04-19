package jwd.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.test.model.Model2;

@Repository
public interface Model2Repository extends JpaRepository<Model2, Long> {
		
}