package br.edu.ifpb.pweb2.cardeneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.cardeneta.entity.Aula;
import br.edu.ifpb.pweb2.cardeneta.entity.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{

}
