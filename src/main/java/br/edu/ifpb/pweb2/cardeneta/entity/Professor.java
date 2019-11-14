package br.edu.ifpb.pweb2.cardeneta.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TB_PROFESSOR")
public class Professor extends Usuario{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	
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
	
}
