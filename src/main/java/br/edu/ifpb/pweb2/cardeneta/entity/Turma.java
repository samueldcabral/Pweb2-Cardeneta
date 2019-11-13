package br.edu.ifpb.pweb2.cardeneta.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_TURMA")
public class Turma {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String codigo;
	
	private Disciplina disciplina;	
	private List<Aula> aulas = new ArrayList<>();
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private Map<String, String> notas = new HashMap<String, String>();
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
	
	public List<Aula> getAulas() {
		return aulas;
	}
	
	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}
	
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public Map<String, String> getNotas() {
		return notas;
	}
	
	public void setNotas(Map<String, String> notas) {
		this.notas = notas;
	}
	
	
}