package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Pneu;
import com.indeas.gestaoempreiteira.modelo.PneuHistorico;

public interface PneuHistoricoRepository extends JpaRepository<PneuHistorico, Long> {

	public List<PneuHistorico> findByPneu(Pneu pneu);
}
