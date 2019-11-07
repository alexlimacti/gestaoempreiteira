package com.indeas.gestaoempreiteira.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.Cliente;
import com.indeas.gestaoempreiteira.modelo.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
	
	public Contrato findByCodigo(String codigo);
	
	@Query("from Contrato c where c.datainicio between :datainicial and :datafinal")
	public List<Contrato> findByDatainicioBetween(@Param("datainicial")Date datainicial, @Param("datafinal")Date datafinal);
	
	@Query("from Contrato c where c.datafim between :datainicial and :datafinal")
	public List<Contrato> findByDatafimBetween(@Param("datainicial")Date datainicial, @Param("datafinal")Date datafinal);
	
	public List<Contrato> findByCliente(Cliente cliente);
	
	public List<Contrato> findByFinalizado(boolean finalizado);
	
}
