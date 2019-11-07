package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.Pneu;
import com.indeas.gestaoempreiteira.modelo.PneuTipo;
import com.indeas.gestaoempreiteira.repositorio.PneuRepository;

@Service
public class PneuService {

	@Autowired
	private PneuRepository pneurepository;
	
	@Autowired
	private LogService logservice;

	public Pneu findByCodigo(String codigo) {
		return pneurepository.findByCodigo(codigo);
	}

	public List<Pneu> findByQuilometragem(Long quilometrageminicial, Long quilometragemfinal) {
		return pneurepository.findByQuilometragem(quilometrageminicial, quilometragemfinal);
	}

	public void salvar(Pneu entity) {
		pneurepository.save(entity);
		logservice.salvar(entity, MenuSistema.pneu, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<Pneu> findAll() {
		return pneurepository.findAll();
	}	

	public Pneu getOne(Long id) {
		return pneurepository.getOne(id);
	}

	public long count() {
		return pneurepository.count();
	}

	public void deletar(Pneu entity) {
		pneurepository.delete(entity);
		logservice.salvar(entity, MenuSistema.pneu, LogAcaoUsuarioSistema.REMOVER);
	}

	public List<Pneu> findByModelo(String modelo) {
		return pneurepository.findByModelo(modelo);
	}

	public int countByPneuTipo(PneuTipo pneutipo) {
		return pneurepository.countByPneuTipo(pneutipo);
	}

	public List<Pneu> findByInutilizavel(boolean inutilizavel) {
		return pneurepository.findByInutilizavel(inutilizavel);
	}
	
	
}
