package br.edu.ifpb.pweb2.cardeneta.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_TURMA")
public class Turma {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String codigo;
	
	@ManyToOne
	private Disciplina disciplina;	
	
	@ManyToOne
	private Professor professor;
	
	@OneToMany
	private List<Aula> aulas = new ArrayList<>();
	
	@OneToMany
	private List<Nota> notas = new ArrayList<Nota>();
	
	@ManyToMany
	private List<Aluno> alunos = new ArrayList<Aluno>();

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
}
