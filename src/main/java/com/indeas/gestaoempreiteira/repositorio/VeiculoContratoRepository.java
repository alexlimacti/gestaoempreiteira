package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Contrato;
import com.indeas.gestaoempreiteira.modelo.Veiculo;
import com.indeas.gestaoempreiteira.modelo.VeiculoContrato;

public interface VeiculoContratoRepository extends JpaRepository<VeiculoContrato, Long> {
	
	public List<VeiculoContrato> findByContrato(Contrato contrato);
	
	public List<VeiculoContrato> findByVeiculo(Veiculo veiculo);

}
