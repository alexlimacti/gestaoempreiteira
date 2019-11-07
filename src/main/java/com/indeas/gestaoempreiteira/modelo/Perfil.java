package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length = 60)
	private String nome;
	
	@Column(length = 200)
	private String observacoes;
	
	@OneToMany
    @JoinColumn(name = "perfilmenu", referencedColumnName = "id")
	private List<PerfilMenu>perfilmenu;

	private boolean diretoria;
	
	private boolean perfilindeas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<PerfilMenu> getPerfilmenu() {
		return perfilmenu;
	}

	public void setPerfilmenu(List<PerfilMenu> perfilmenu) {
		this.perfilmenu = perfilmenu;
	}

	public boolean isDiretoria() {
		return diretoria;
	}

	public void setDiretoria(boolean diretoria) {
		this.diretoria = diretoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isPerfilindeas() {
		return perfilindeas;
	}

	public void setPerfilindeas(boolean perfilindeas) {
		this.perfilindeas = perfilindeas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
