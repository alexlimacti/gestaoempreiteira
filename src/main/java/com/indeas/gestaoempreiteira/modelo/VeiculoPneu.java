package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="veiculopneu")
public class VeiculoPneu implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="veiculo", referencedColumnName="codigo")
	private Veiculo veiculo;
	
	@ManyToOne
	@JoinColumn(name="pneu", referencedColumnName="codigo")
	private Pneu pneu;

	@Enumerated(EnumType.STRING)
	private VeiculoEixos veiculoeixos;
	
	@Temporal(TemporalType.DATE)
	private Date datatroca;
	
	private long quilometragem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Pneu getPneu() {
		return pneu;
	}

	public void setPneu(Pneu pneu) {
		this.pneu = pneu;
	}

	public VeiculoEixos getVeiculoeixos() {
		return veiculoeixos;
	}

	public void setVeiculoeixos(VeiculoEixos veiculoeixos) {
		this.veiculoeixos = veiculoeixos;
	}

	public Date getDatatroca() {
		return datatroca;
	}

	public void setDatatroca(Date datatroca) {
		this.datatroca = datatroca;
	}

	public long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(long quilometragem) {
		this.quilometragem = quilometragem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		VeiculoPneu other = (VeiculoPneu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
