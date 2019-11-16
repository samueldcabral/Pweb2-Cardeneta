package br.edu.ifpb.pweb2.cardeneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.cardeneta.entity.Aluno;
import br.edu.ifpb.pweb2.cardeneta.entity.Professor;

public interface AlunoRepository extends JpaRepository<Professor, Long>{

	Aluno findByLogin(String login);

}
