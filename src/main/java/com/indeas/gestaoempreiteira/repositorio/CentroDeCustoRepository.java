package com.indeas.gestaoempreiteira.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.CentroDeCusto;
import com.indeas.gestaoempreiteira.modelo.Usuario;

public interface CentroDeCustoRepository  extends JpaRepository<CentroDeCusto, Long> {
	
	public CentroDeCusto findByCentrodecusto(String CentroDeCusto);
	
	@Query("from CentroDeCusto c where"
			+ " c.responsavel = :responsavel or responsavelalternativo = :responsavel")
	public CentroDeCusto findByResponsavelOrResponsavelalternativo(@Param("responsavel")Usuario usuario);

}
