package jwd.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.test.model.Model1;

@Repository
public interface Model1Repository extends JpaRepository<Model1, Long> {
		
	@Query("SELECT l FROM Model1 l WHERE "
			+ "(:destinacija IS NULL or l.destinacija like :destinacija ) AND "
			+ "(:prevoznikId IS NULL OR l.prevoznik.id = :prevoznikId) AND "
			+ "(:maksCena IS NULL or l.cenaKarte <= :maksCena ) "
			)
	Page<Model1> search(
			@Param("destinacija") String destinacija, 
			@Param("prevoznikId") Long prevoznikId, 
			@Param("maksCena") Double maksCena,
			Pageable pageRequest);
}