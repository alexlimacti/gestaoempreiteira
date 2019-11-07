package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.Servico;
import com.indeas.gestaoempreiteira.repositorio.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository servicorepository;
	
	@Autowired
	private LogService logservice;

	public Servico findByCodigo(String codigo) {
		return servicorepository.findByCodigo(codigo);
	}

	public List<Servico> findByDescricaoContaining(String descricao) {
		return servicorepository.findByDescricaoStartingWithIgnoreCase(descricao);
	}

	public void salvar(Servico entity) {
		servicorepository.save(entity);
		logservice.salvar(entity, MenuSistema.servico, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<Servico> findAll() {
		return servicorepository.findAll();
	}

	public Servico findOne(Long id) {
		return servicorepository.findOne(id);
	}

	public List<Servico> findAll(Sort sort) {
		return servicorepository.findAll(sort);
	}

	public long count() {
		return servicorepository.count();
	}

	public void delete(Servico entity) {
		servicorepository.delete(entity);
		logservice.salvar(entity, MenuSistema.servico, LogAcaoUsuarioSistema.REMOVER);
	}
	
	
	

}
