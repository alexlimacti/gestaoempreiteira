package com.indeas.gestaoempreiteira.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.ProtocoloTipoItem;

public interface ProtocoloTipoItemRepository extends JpaRepository<ProtocoloTipoItem, Long> {

	public ProtocoloTipoItem findByDescricao(String descricao);
	
	public ProtocoloTipoItem findByDescricaoContaining(String descricao);

}
