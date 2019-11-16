package br.edu.ifpb.pweb2.cardeneta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.cardeneta.entity.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {

	List<Turma> findByProfessorId(Long id);
	
	List<Turma> findByProfessorLogin(String login);

}
