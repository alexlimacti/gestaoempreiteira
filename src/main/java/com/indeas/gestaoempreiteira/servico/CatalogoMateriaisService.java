package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.CatalogoMateriais;
import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.repositorio.CatalogoMateriaisRepository;

@Service
public class CatalogoMateriaisService {
	
	@Autowired private LogService logservice;
	
	@Autowired private CatalogoMateriaisRepository catalogomateriaisrepository;

	public CatalogoMateriais findByCodigo(String codigo) {
		return catalogomateriaisrepository.findByCodigo(codigo);
	}

	public void salvar(CatalogoMateriais entity) {
		catalogomateriaisrepository.save(entity);
		logservice.salvar(entity, MenuSistema.catalogodemateriais, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<CatalogoMateriais> findAll() {
		return catalogomateriaisrepository.findAll();
	}

	public CatalogoMateriais findOne(Long id) {
		return catalogomateriaisrepository.findOne(id);
	}

	public long count() {
		return catalogomateriaisrepository.count();
	}

	public void deletar(CatalogoMateriais entity) {
		catalogomateriaisrepository.delete(entity);
		logservice.salvar(entity, MenuSistema.catalogodemateriais, LogAcaoUsuarioSistema.REMOVER);
	}

	public List<CatalogoMateriais> findByDescricaoStartingWithIgnoreCase(String descricao) {
		return catalogomateriaisrepository.findByDescricaoStartingWithIgnoreCase(descricao);
	}	

}
