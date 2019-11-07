package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.Marca;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.TipoEquipamento;
import com.indeas.gestaoempreiteira.modelo.VeiculoTipo;
import com.indeas.gestaoempreiteira.repositorio.VeiculoTipoRepository;

@Service
public class VeiculoTipoService {
	
	@Autowired
	private VeiculoTipoRepository veiculotiporepository;
	
	@Autowired
	private LogService logservice;

	public VeiculoTipo findByCodigo(String codigo) {
		return veiculotiporepository.findByCodigo(codigo);
	}

	public List<VeiculoTipo> findByMarca(Marca marca) {
		return veiculotiporepository.findByMarca(marca);
	}

	public List<VeiculoTipo> findByDescricaoContaining(String descricao) {
		return veiculotiporepository.findByDescricaoContaining(descricao);
	}

	public void save(VeiculoTipo entity) {
		veiculotiporepository.save(entity);
		logservice.salvar(entity, MenuSistema.veiculotipo, LogAcaoUsuarioSistema.SALVAR);
	}

	public VeiculoTipo findOne(Long id) {
		return veiculotiporepository.findOne(id);
	}

	public long count() {
		return veiculotiporepository.count();
	}

	public void delete(VeiculoTipo entity) {
		veiculotiporepository.delete(entity);
		logservice.salvar(entity, MenuSistema.veiculotipo, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<VeiculoTipo> findAll() {
		return veiculotiporepository.findAll();
	}

	public List<VeiculoTipo> findByTipoequipamento(TipoEquipamento tipoequipamento) {
		return veiculotiporepository.findByTipoequipamento(tipoequipamento);
	}
	
	

}
