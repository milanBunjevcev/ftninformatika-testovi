package jwd.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.test.model.Zgrada;

@Repository
public interface ZgradaRepository extends JpaRepository<Zgrada, Long> {
		
}