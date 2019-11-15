package br.edu.ifpb.pweb2.cardeneta.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_ALUNO")
public class Aluno extends Usuario {
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String matricula;
	
	@ManyToMany(mappedBy="alunos")
	private List<Turma> turmas;
	
	@OneToMany
	private List<Nota> notas;

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	} 

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}
