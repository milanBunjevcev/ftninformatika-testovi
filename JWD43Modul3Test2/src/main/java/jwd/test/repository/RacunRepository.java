package jwd.test.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.test.model.Racun;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long> {
		
	@Query("SELECT l FROM Racun l WHERE "
			+ "(:jmbg IS NULL or l.jmbg like :jmbg ) AND "
			+ "(:bankaId IS NULL OR l.banka.id = :bankaId) "
			)
	Page<Racun> search(
			@Param("jmbg") String jmbg, 
			@Param("bankaId") Long bankaId, 			
			Pageable pageRequest);

	List<Racun> findByBrojRacuna(String brojRacuna1);
}