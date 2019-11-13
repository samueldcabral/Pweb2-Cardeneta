package br.edu.ifpb.pweb2.cardeneta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TB_ALUNO")
public class Aluno extends Usuario {
	
	@Column(name="AL_NOME")
	private String nome;
	
	@Column(name="AL_MATRICULA")
	private String matricula;
	

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
