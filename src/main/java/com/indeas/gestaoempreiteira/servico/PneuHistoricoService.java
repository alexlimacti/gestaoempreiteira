package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.Pneu;
import com.indeas.gestaoempreiteira.modelo.PneuHistorico;
import com.indeas.gestaoempreiteira.repositorio.PneuHistoricoRepository;

@Service
public class PneuHistoricoService {
	
	@Autowired
	private PneuHistoricoRepository pneuhistoricorepository;
	
	@Autowired
	private LogService logservice;

	public List<PneuHistorico> findByPneu(Pneu pneu) {
		return pneuhistoricorepository.findByPneu(pneu);
	}

	public void salvar(PneuHistorico entity) {
		pneuhistoricorepository.save(entity);
		logservice.salvar(entity, MenuSistema.pneuhistorico, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<PneuHistorico> findAll() {
		return pneuhistoricorepository.findAll();
	}

	public PneuHistorico findOne(Long id) {
		return pneuhistoricorepository.findOne(id);
	}

	public long count() {
		return pneuhistoricorepository.count();
	}

	public void delete(PneuHistorico entity) {
		pneuhistoricorepository.delete(entity);
		logservice.salvar(entity, MenuSistema.pneuhistorico, LogAcaoUsuarioSistema.REMOVER);
	}
	
	

}
