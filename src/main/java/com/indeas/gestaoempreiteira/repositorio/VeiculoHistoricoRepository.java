package com.indeas.gestaoempreiteira.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.Veiculo;
import com.indeas.gestaoempreiteira.modelo.VeiculoHistorico;

public interface VeiculoHistoricoRepository extends JpaRepository<VeiculoHistorico, Long> {
	
	public List<VeiculoHistorico> findByVeiculo(Veiculo veiculo);
	
	@Query("from VeiculoHistorico vh"
			+ " where vh.veiculo = :veiculo"
			+ " and vh.data between :datainicial and :datafinal")
	public List<VeiculoHistorico> findByVeiculoAndData(@Param("veiculo")Veiculo veiculo, @Param("datainicial")Date datainicial, @Param("datafinal")Date datafinal);

}
