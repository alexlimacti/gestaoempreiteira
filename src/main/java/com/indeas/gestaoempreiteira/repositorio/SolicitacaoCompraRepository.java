package com.indeas.gestaoempreiteira.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.CatalogoMateriais;
import com.indeas.gestaoempreiteira.modelo.SolicitacaoCompra;

public interface SolicitacaoCompraRepository extends JpaRepository<SolicitacaoCompra, Long> {

	public SolicitacaoCompra findByCodigo(String codigo);
	
	public List<SolicitacaoCompra> findByMaterial(CatalogoMateriais material);
	
	public List<SolicitacaoCompra> findByAtendida(boolean atendida);
	
	@Query("from SolicitacaoCompra s"
			+ " where s.atendida = :atendida"
			+ " AND dataatendimento between :datainicial and :datafinal")
	public List<SolicitacaoCompra> findByAtendidaAndDataatendimento(@Param("atendida")boolean atendida, @Param("datainicial")Date datainicial, @Param("datafinal")Date datafinal);
	
	@Query("select count(s) from SolicitacaoCompra s"
			+ " where s.atendida = :atendia")
	public int countByAutorAndStatus(@Param("atendia")boolean atendida);

}
