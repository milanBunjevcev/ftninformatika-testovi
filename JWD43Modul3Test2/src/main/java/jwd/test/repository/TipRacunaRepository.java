package jwd.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.test.model.TipRacuna;

@Repository
public interface TipRacunaRepository extends JpaRepository<TipRacuna, Long> {

	List<TipRacuna> findByBankaId(Long id);
		
}