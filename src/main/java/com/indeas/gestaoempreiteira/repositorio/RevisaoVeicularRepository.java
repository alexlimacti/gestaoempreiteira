package com.indeas.gestaoempreiteira.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.RevisaoVeicular;
import com.indeas.gestaoempreiteira.modelo.Veiculo;

public interface RevisaoVeicularRepository extends JpaRepository<RevisaoVeicular, Long> {

	public RevisaoVeicular findByCodigo(String codigo);
	
	public List<RevisaoVeicular> findByVeiculo(Veiculo veiculo);
	
	@Query("from RevisaoVeicular v"
			+ " where v.datarevisao between :datainicio and :datafim")
	public List<RevisaoVeicular> findByDatarevisao(@Param("datainicio")Date datainicio, @Param("datafim")Date datafim);
	
	@Query("from RevisaoVeicular v"
			+ " where v.veiculo = :veiculo"
			+ " and v.datarevisao between :datainicio and :datafim")
	public List<RevisaoVeicular> findByVeiculoAndDatarevisao(@Param("veiculo")Veiculo veiculo, @Param("datainicio")Date datainicio, @Param("datafim")Date datafim);

}
