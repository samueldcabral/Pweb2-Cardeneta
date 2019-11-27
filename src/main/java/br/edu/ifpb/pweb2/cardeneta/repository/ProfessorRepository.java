package br.edu.ifpb.pweb2.cardeneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.cardeneta.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{

	Professor findByUsuarioId(Long id);

}
