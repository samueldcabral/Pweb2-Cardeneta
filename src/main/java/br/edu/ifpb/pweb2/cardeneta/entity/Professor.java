package br.edu.ifpb.pweb2.cardeneta.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_PROFESSOR")
public class Professor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@OneToMany
	private List<Turma> turmas = new ArrayList<Turma>();
	
	public Integer getId() {
		return id;
	}
	public List<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
