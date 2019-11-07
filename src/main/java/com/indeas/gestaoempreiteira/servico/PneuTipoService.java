package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.PneuTipo;
import com.indeas.gestaoempreiteira.repositorio.PneuTipoRepository;

@Service
public class PneuTipoService {
	
	@Autowired
	private PneuTipoRepository tipopneurepository;
	
	@Autowired
	private LogService logservice;

	public PneuTipo findBySigla(String Sigla) {
		return tipopneurepository.findBySigla(Sigla);
	}

	public List<PneuTipo> findByDescricaoContaining(String descricao) {
		return tipopneurepository.findByDescricaoContaining(descricao);
	}

	public void salvar(PneuTipo entity) {
		tipopneurepository.save(entity);
		logservice.salvar(entity, MenuSistema.pneutipo, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<PneuTipo> findAll() {
		return tipopneurepository.findAll();
	}

	public PneuTipo findOne(Long id) {
		return tipopneurepository.findOne(id);
	}

	public long count() {
		return tipopneurepository.count();
	}

	public void deletar(PneuTipo entity) {
		tipopneurepository.delete(entity);
		logservice.salvar(entity, MenuSistema.pneutipo, LogAcaoUsuarioSistema.REMOVER);
	}
	
	

}
