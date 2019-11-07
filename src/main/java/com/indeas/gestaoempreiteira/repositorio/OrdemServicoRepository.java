package com.indeas.gestaoempreiteira.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.OrdemServico;
import com.indeas.gestaoempreiteira.modelo.Status;
import com.indeas.gestaoempreiteira.modelo.Usuario;
import com.indeas.gestaoempreiteira.modelo.Veiculo;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

	public OrdemServico findByCodigo(String codigo);
	
	public List<OrdemServico> findByVeiculo(Veiculo veiculo);
	
	public List<OrdemServico> findByFinalizado(boolean finalizado);
	
	public List<OrdemServico> findByStatus(Status status);
	
	@Query("from OrdemServico o where o.status = :status"
			+ " AND o.dataos between :datainicio and :datafim")
	public List<OrdemServico> findByStatusAndDataos(@Param("status")Status status, @Param("datainicio")Date datainicio, @Param("datafim")Date datafim);
	
	@Query("from OrdemServico o where o.veiculo = :veiculo"
			+ " AND o.dataos between :datainicio and :datafim")
	public List<OrdemServico> findByVeiculoAndDataos(@Param("veiculo")Veiculo veiculo, @Param("datainicio")Date datainicio, @Param("datafim")Date datafim);
	
	public List<OrdemServico> findByUsuarioresponsavelos(Usuario usuario);
	
	public List<OrdemServico> findByUsuariosolicitante(Usuario usuario);

}
