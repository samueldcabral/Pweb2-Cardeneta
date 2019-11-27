package br.edu.ifpb.pweb2.cardeneta.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_TURMA")
public class Turma {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	private Disciplina disciplina;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Professor professor;
	
	@OneToMany
	private List<Aula> aulas = new ArrayList<>();
	
	@OneToMany
	private List<Nota> notas = new ArrayList<Nota>();
	
	@ManyToMany
	private List<Aluno> alunos = new ArrayList<Aluno>();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
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

	public void addAulas(Aula aula) {
		this.aulas.add(aula);
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	
	public void addNotas(Nota nota) {
		this.notas.add(nota);
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public void addAlunos(Aluno alunos) {
		this.alunos.add(alunos);
	}
	
}
