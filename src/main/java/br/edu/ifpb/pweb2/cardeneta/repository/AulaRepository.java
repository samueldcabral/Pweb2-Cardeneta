package br.edu.ifpb.pweb2.cardeneta.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.edu.ifpb.pweb2.cardeneta.entity.Aula;

public interface AulaRepository extends JpaRepository<Aula, Integer>{

	List<Aula> findByAssuntoStartingWithIgnoreCase(String assunto);
	
	@Query("from Aula where lower(assunto) like lower(concat('%',:assunto,'%'))")
	List<Aula> findByAssunto(@Param("assunto") String assunto);
	
}
