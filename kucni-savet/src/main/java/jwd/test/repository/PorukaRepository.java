package jwd.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.test.model.Poruka;

@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long> {
		
	@Query("SELECT l FROM Poruka l WHERE "
			+ "(:naslov IS NULL or l.naslov like :naslov ) AND "
			+ "(:zgradaId IS NULL OR l.zgrada.id = :zgradaId) AND "
			+ "(:tip IS NULL or l.tip like :tip ) "
			)
	Page<Poruka> search(			
			@Param("zgradaId") Long zgradaId, 
			@Param("naslov") String naslov, 
			@Param("tip") String tip,
			Pageable pageRequest);
}