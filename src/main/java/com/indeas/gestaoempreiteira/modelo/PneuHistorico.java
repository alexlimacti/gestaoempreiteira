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
import javax.persistence.Version;

@Entity
@Table(name = "pneuhistorico")
public class PneuHistorico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Long version;

	@ManyToOne
	@JoinColumn(name = "pneu", referencedColumnName = "codigo")
	private Pneu pneu;

	private Long quilometragem;
	private Long quilometragemrodado;

	@Temporal(TemporalType.DATE)
	private Date datahistorico;

	/*
	 * Movimentacao dos pneus
	 */
	@Enumerated(EnumType.STRING)
	private VeiculoEixos eixo;

	@ManyToOne
	@JoinColumn(name = "veiculo", referencedColumnName = "codigo")
	private Veiculo veiculo;

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

	public Pneu getPneu() {
		return pneu;
	}

	public void setPneu(Pneu pneu) {
		this.pneu = pneu;
	}

	public Long getQuilometragemrodado() {
		return quilometragemrodado;
	}

	public void setQuilometragemrodado(Long quilometragemrodado) {
		this.quilometragemrodado = quilometragemrodado;
	}

	public Date getDatahistorico() {
		return datahistorico;
	}

	public void setDatahistorico(Date datahistorico) {
		this.datahistorico = datahistorico;
	}

	public VeiculoEixos getEixo() {
		return eixo;
	}

	public void setEixo(VeiculoEixos eixo) {
		this.eixo = eixo;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	public boolean isNovo() {
		return id == null;
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
		PneuHistorico other = (PneuHistorico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
