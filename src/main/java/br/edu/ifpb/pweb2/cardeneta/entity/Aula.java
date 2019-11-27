package br.edu.ifpb.pweb2.cardeneta.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="TB_AULA")
public class Aula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String assunto;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date data;
	
	@ManyToOne
	private Turma turma;
	
	@ManyToMany
	private List<Aluno> alunosPresentes;
	
	public Long getId() {
		return id;
	}

	public Turma getTurma() {
		return turma;
	}
	
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAssunto() {
		return assunto;
	}
	
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	public List<Aluno> getAlunosPresentes() {
		return alunosPresentes;
	}

	public void setAlunosPresentes(List<Aluno> alunosPresentes) {
		this.alunosPresentes = alunosPresentes;
	}
	
	public void addAlunosPresentes(Aluno alunoPresente) {
		this.alunosPresentes.add(alunoPresente);
	}
	
	
}
