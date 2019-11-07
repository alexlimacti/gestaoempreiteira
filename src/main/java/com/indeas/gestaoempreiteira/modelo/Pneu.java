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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="pneu")
public class Pneu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(length = 20, unique = true)
	private String codigo;
	
	@NotNull(message="A marca é obrigatória")
	@ManyToOne
	@JoinColumn(name="marca")
	private Marca marca;
	
	@NotNull(message="O Tipo do Pneu é obrigatório")
	@ManyToOne
	@JoinColumn(name="tipo", referencedColumnName="sigla")
	private PneuTipo tipo;
	
	private boolean inutilizavel;
	
	@NotNull(message="Quilometragem é obrigatória")
	private Long quilometragem;
	
	@NotNull(message="Data de vencimento é obrigatória")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date datacompra;
	
	@NotBlank(message="Modelo é obrigatória")
	@Column(length=100)
	private String modelo;
	
	@NotBlank(message="Número de Série é obrigatório")
	@Column(length=100, unique=true)
	private String numeroserie;

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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isInutilizavel() {
		return inutilizavel;
	}

	public void setInutilizavel(boolean inutilizavel) {
		this.inutilizavel = inutilizavel;
	}

	public Date getDatacompra() {
		return datacompra;
	}

	public void setDatacompra(Date datacompra) {
		this.datacompra = datacompra;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroserie() {
		return numeroserie;
	}

	public void setNumeroserie(String numeroserie) {
		this.numeroserie = numeroserie;
	}
	
	public boolean isNovo() {
		return id == null;
	}

	public PneuTipo getTipo() {
		return tipo;
	}

	public void setTipo(PneuTipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Pneu other = (Pneu) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}
