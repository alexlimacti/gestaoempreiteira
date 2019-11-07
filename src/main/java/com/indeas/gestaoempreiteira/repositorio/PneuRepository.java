package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.Pneu;
import com.indeas.gestaoempreiteira.modelo.PneuTipo;

public interface PneuRepository extends JpaRepository<Pneu, Long> {

	public Pneu findByCodigo(String codigo);
	
	@Query("from Pneu p"
			+ " where p.quilometragem between :quilometrageminicial and :quilometragemfinal")
	public List<Pneu> findByQuilometragem(@Param("quilometrageminicial")Long quilometrageminicial,@Param("quilometragemfinal")Long quilometragemfinal);
	
	@Query("select pn from Pneu pn where pn.modelo like %:modelo%")
	public List<Pneu> findByModelo(@Param("modelo")String modelo);
	
	@Query("select count(p) from Pneu p where p.tipo = :tipo")
	public int countByPneuTipo(@Param("tipo") PneuTipo pneutipo);
	
	public List<Pneu> findByInutilizavel(boolean inutilizavel);
	
}
