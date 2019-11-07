package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Contrato;
import com.indeas.gestaoempreiteira.modelo.ContratoItem;

public interface ContratoItemRepository extends JpaRepository<ContratoItem, Long> {

	public List<ContratoItem> findByContrato(Contrato contrato);

}
