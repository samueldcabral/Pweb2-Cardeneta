package br.edu.ifpb.pweb2.cardeneta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.cardeneta.entity.Disciplina;
import br.edu.ifpb.pweb2.cardeneta.entity.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {

	List<Turma> findByProfessorId(Long id);

	Optional<Turma> findTurmaByCodigo(Long codigo);
	
	Optional<Turma> findByDisciplina(Disciplina disciplina);

	
//	List<Turma> findByProfessorLogin(String login);

}
