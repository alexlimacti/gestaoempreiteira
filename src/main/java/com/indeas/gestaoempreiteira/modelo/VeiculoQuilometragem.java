package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;
import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="veiculoquilometragem")
public class VeiculoQuilometragem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@NotNull(message="O Equipamento é obrigatório")
	@ManyToOne
	@JoinColumn(name="veiculo", referencedColumnName="codigo")
	private Veiculo veiculo;
	
	@NotNull(message="A Quilometragem Inicial é obrigatória")
	private Long quilometrageminicial;
	
	@NotNull(message="A Quilometragem Final é obrigatória")
	private Long quilometragemfinal;
	
	@NotNull(message="A Data de Recolhimento é obrigatória")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Temporal(TemporalType.DATE)
	private Date datacadastro;

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

	public Long getQuilometrageminicial() {
		return quilometrageminicial;
	}

	public void setQuilometrageminicial(Long quilometrageminicial) {
		this.quilometrageminicial = quilometrageminicial;
	}

	public Long getQuilometragemfinal() {
		return quilometragemfinal;
	}

	public void setQuilometragemfinal(Long quilometragemfinal) {
		this.quilometragemfinal = quilometragemfinal;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDatacadastro() {
		return datacadastro;
	}
	
	public boolean isNovo() {
		return id == null;
	}

	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
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
		VeiculoQuilometragem other = (VeiculoQuilometragem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
