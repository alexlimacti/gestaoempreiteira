package com.indeas.gestaoempreiteira.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="veiculocontrato")
public class VeiculoContrato implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="veiculo", referencedColumnName="codigo")
	private Veiculo veiculo;
	
	@ManyToOne
	@JoinColumn(name="contrato", referencedColumnName="codigo")
	private Contrato contrato;

}
