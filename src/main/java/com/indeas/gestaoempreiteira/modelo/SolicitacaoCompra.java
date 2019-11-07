package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="solicitacaocompra")
public class SolicitacaoCompra implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(length=20, unique=true)
	private String codigo;
	
	@ManyToOne
	@JoinColumn(name="material", referencedColumnName="codigo")
	private CatalogoMateriais material;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datasolicitacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataatendimento;

	private boolean atendida;
	
	private Long quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public CatalogoMateriais getMaterial() {
		return material;
	}

	public void setMaterial(CatalogoMateriais material) {
		this.material = material;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDatasolicitacao() {
		return datasolicitacao;
	}

	public void setDatasolicitacao(Date datasolicitacao) {
		this.datasolicitacao = datasolicitacao;
	}

	public Date getDataatendimento() {
		return dataatendimento;
	}

	public void setDataatendimento(Date dataatendimento) {
		this.dataatendimento = dataatendimento;
	}

	public boolean isAtendida() {
		return atendida;
	}

	public void setAtendida(boolean atendida) {
		this.atendida = atendida;
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
		SolicitacaoCompra other = (SolicitacaoCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
