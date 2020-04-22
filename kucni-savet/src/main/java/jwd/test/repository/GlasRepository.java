package jwd.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.test.model.Glas;

@Repository
public interface GlasRepository extends JpaRepository<Glas, Long> {
		
}