package br.edu.ifpb.pweb2.cardeneta.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_DISCIPLINA")
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	private String nome;
	private String curso;
	private Long cargaHoraria;
	
	@OneToMany
	private List<Turma> turmas = new ArrayList<Turma>();
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Long getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Long cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public void adicionarTurma(Turma turma) {
		this.turmas.add(turma);
	}
	public List<Turma> getTurmas() {
		return turmas;
	}
	

}
