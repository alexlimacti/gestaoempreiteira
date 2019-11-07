package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Protocolo;
import com.indeas.gestaoempreiteira.modelo.ProtocoloItens;

public interface ProtocoloItemRepository extends JpaRepository<ProtocoloItens, Long> {

	public List<ProtocoloItens> findByProtocolo(Protocolo protocolo);

}
