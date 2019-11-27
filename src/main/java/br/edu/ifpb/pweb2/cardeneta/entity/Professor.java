package br.edu.ifpb.pweb2.cardeneta.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TB_PROFESSOR")
public class Professor implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;	
//	private String login;
//	private String senha;
	
    @OneToMany
	private List<Turma> turmas = new ArrayList<Turma>(); 
    
    @OneToOne
    private Usuario usuario;
    
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
    public List<Turma> getTurmas() {
		return turmas;
	}
    
	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
//	public String getLogin() {
//		return login;
//	}
//	public void setLogin(String login) {
//		this.login = login;
//	}
//	public String getSenha() {
//		return senha;
//	}
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
	
}
