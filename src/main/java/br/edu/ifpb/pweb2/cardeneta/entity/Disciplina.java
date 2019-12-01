package br.edu.ifpb.pweb2.cardeneta.entity;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 927027e4fbce2395dbfe866c1bcfa8548bc747c2
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
<<<<<<< HEAD
	private Long cargaHoraria;
	
	@OneToMany
	private List<Turma> turmas = new ArrayList<Turma>();
=======
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Turma> turmas;
>>>>>>> 927027e4fbce2395dbfe866c1bcfa8548bc747c2
	
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
<<<<<<< HEAD
	public Long getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Long cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public void adicionarTurma(Turma turma) {
		this.turmas.add(turma);
	}
	
	public Turma getTurma(Long id) {
		for(Turma tur : turmas) {
			if(tur.getCodigo() == id) {
				return tur;
			}
		}
		return null;
	}
	
	public void updateTurma(Turma antigo, Turma novo) {
		this.turmas.remove(antigo);
		this.adicionarTurma(novo);
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}
	

=======
	
	
	
>>>>>>> 927027e4fbce2395dbfe866c1bcfa8548bc747c2
}
