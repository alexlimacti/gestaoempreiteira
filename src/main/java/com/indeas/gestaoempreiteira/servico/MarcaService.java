package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.Marca;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.repositorio.MarcaRepository;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcarepository;
	
	@Autowired
	private LogService logservice;

	public void salvar(Marca entity) {
		marcarepository.save(entity);
		logservice.salvar(entity, MenuSistema.marca, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<Marca> findAll() {
		return marcarepository.findAll();
	}

	public Marca findOne(Long id) {
		return marcarepository.findOne(id);
	}

	public long count() {
		return marcarepository.count();
	}
	
	

	public void delete(Long id) {
		marcarepository.delete(id);
		logservice.salvar(findOne(id), MenuSistema.marca, LogAcaoUsuarioSistema.REMOVER);
	}

	public void deletar(Marca entity) {
		marcarepository.delete(entity);
		logservice.salvar(entity, MenuSistema.marca, LogAcaoUsuarioSistema.REMOVER);
	}

	public Marca findByDescricao(String descricao) {
		return marcarepository.findByDescricao(descricao);
	}

	public List<Marca> findByDescricaoContaining(String descricao) {
		return marcarepository.findByDescricaoContaining(descricao);
	}
	
	

}
