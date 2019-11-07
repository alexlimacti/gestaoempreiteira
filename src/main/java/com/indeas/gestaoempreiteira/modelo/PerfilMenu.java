package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="perfilmenu")
public class PerfilMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@OneToOne
	@JoinColumn(name="perfilmenu", referencedColumnName="id")
	private Perfil perfilmenu;
	
	@Enumerated(EnumType.STRING)
	private MenuSistema menu;

	private boolean somenteleitura;
	
	private boolean excluir;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Perfil getPerfilmenu() {
		return perfilmenu;
	}

	public void setPerfilmenu(Perfil perfilmenu) {
		this.perfilmenu = perfilmenu;
	}

	public MenuSistema getMenu() {
		return menu;
	}

	public void setMenu(MenuSistema menu) {
		this.menu = menu;
	}

	public boolean isSomenteleitura() {
		return somenteleitura;
	}

	public void setSomenteleitura(boolean somenteleitura) {
		this.somenteleitura = somenteleitura;
	}

	public boolean isExcluir() {
		return excluir;
	}

	public void setExcluir(boolean excluir) {
		this.excluir = excluir;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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
		PerfilMenu other = (PerfilMenu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
