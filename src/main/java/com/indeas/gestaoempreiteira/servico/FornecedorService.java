package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.Fornecedor;
import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.repositorio.FornecedorRepository;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	LogService logService;

	public void salvar(Fornecedor fornecedor) {
		fornecedorRepository.save(fornecedor);
		logService.salvar(fornecedor,MenuSistema.fornecedor,LogAcaoUsuarioSistema.SALVAR);
	}
	
	public void excluir(Fornecedor fornecedor) {
		fornecedorRepository.delete(fornecedor);
		logService.salvar(fornecedor,MenuSistema.fornecedor,LogAcaoUsuarioSistema.REMOVER);
	}

	public List<Fornecedor> findAll() {
		return fornecedorRepository.findAll();
	}

	public List<Fornecedor> findByRazaosocialStartingWithIgnoreCase(String nome) {
		return fornecedorRepository.findByRazaosocialStartingWithIgnoreCase(nome);
	}
	

}
