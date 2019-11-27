package br.edu.ifpb.pweb2.cardeneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.cardeneta.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	Aluno findByUsuarioId(Long parseLong);

//	Aluno findByLogin(String login);

}
