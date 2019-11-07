package com.indeas.gestaoempreiteira.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.Veiculo;
import com.indeas.gestaoempreiteira.modelo.VeiculoQuilometragem;

public interface VeiculoQuilometragemRepository extends JpaRepository<VeiculoQuilometragem, Long> {
	
	public List<VeiculoQuilometragem> findByVeiculo(Veiculo veiculo);
	
	@Query("from VeiculoQuilometragem vq"
			+ " where vq.veiculo = :veiculo"
			+ " and vq.data between :datainicial and :datafinal")
	public List<VeiculoQuilometragem> findByVeiculoAndData(@Param("veiculo")Veiculo veiculo, @Param("datainicial")Date datainicial, @Param("datafinal")Date datafinal);
	
	public List<VeiculoQuilometragem> findByDatacadastro(Date datacadastro);

}
