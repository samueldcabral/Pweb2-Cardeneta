package br.edu.ifpb.pweb2.cardeneta.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TB_COORDENADOR")
public class Coordenador extends Professor{
	
	private static final long serialVersionUID = 1L;
	
	private Boolean ativo;

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
