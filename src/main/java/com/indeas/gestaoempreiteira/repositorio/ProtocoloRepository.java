package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.Protocolo;
import com.indeas.gestaoempreiteira.modelo.Status;
import com.indeas.gestaoempreiteira.modelo.Usuario;

public interface ProtocoloRepository extends JpaRepository<Protocolo, Long> {

	public Protocolo findByProtocolo(String protocolo);
	
	@Query("from Protocolo p where p.destinatario = :autor"
			+ " AND (YEAR(p.datatramite) = :ano"
			+ " AND MONTH(p.datatramite) = :mes"
			+ " AND p.status <> 'aguardandoenvio'"
			+ " OR p.status = 'emtramite'"
			+ " OR p.status = 'visualizado')")
	public List<Protocolo> findByDestinatario(@Param("mes")int mes, @Param("ano")int ano, @Param("autor") Usuario autor);
	
	@Query("from Protocolo p where p.autor = :autor"
			+ " AND (YEAR(p.datatramite) = :ano"
			+ " AND MONTH(p.datatramite) = :mes"
			+ " AND p.status <> 'aguardandoenvio'"
			+ " OR p.status = 'emtramite'"
			+ " OR p.status = 'visualizado')")
	public List<Protocolo> findByAutor(@Param("mes")int mes, @Param("ano")int ano, @Param("autor") Usuario autor);
	
	@Query("from Protocolo p where p.autor = :autor"
			+ " AND p.status = :status")
	public List<Protocolo> findByAutorAndStatus(@Param("autor") Usuario autor, @Param("status") Status status);
	
	@Query("select count(p) from Protocolo p where p.destinatario = :autor"
			+ " AND p.status = :status")
	public int countByDestinatarioAndStatus(@Param("autor") Usuario autor, @Param("status") Status status);
	
	@Query("select count(p) from Protocolo p where p.autor = :autor"
			+ " AND p.status = :status")
	public int countByAutorAndStatus(@Param("autor") Usuario autor, @Param("status") Status status);

}
