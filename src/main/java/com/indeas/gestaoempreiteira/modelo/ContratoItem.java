package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name="contratoitem")
public class ContratoItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;

	@ManyToOne
	@JoinColumn(name="contrato", referencedColumnName="codigo")
	private Contrato contrato;
	
	@ManyToOne
	@JoinColumn(name="tipoveiculo", referencedColumnName="codigo")
	private VeiculoTipo tipoveiculo;
	
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal valor;

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

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public VeiculoTipo getTipoveiculo() {
		return tipoveiculo;
	}

	public void setTipoveiculo(VeiculoTipo tipoveiculo) {
		this.tipoveiculo = tipoveiculo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
		ContratoItem other = (ContratoItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
