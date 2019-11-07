package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Documentos;
import com.indeas.gestaoempreiteira.modelo.TipoDocumento;
import com.indeas.gestaoempreiteira.modelo.Usuario;

public interface DocumentosRepository extends JpaRepository<Documentos, Long> {

	public Documentos findByCodigoidentificacao(String codigoidentificacao);
	
	public List<Documentos> findByTipodocumento(TipoDocumento tipodocumento);
	
	public List<Documentos> findByUsuariocriador(Usuario usuario);

}
