package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.Licenca;
import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.repositorio.LicencaRepository;

@Service
public class LicencaService {
	
	@Autowired
	private LicencaRepository licencarepository;
	
	@Autowired
	private LogService logservice;
	
	/*
	@Autowired
	private DadosCliente dadoscliente;
	
	@Autowired
	private Licenciamento licenciamento;
	*/

	public void salvar(Licenca entity) {
		licencarepository.save(entity);
		logservice.salvar(entity, MenuSistema.licenca, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<Licenca> findAll() {
		return licencarepository.findAll();
	}

	public Licenca findOne(Long id) {
		return licencarepository.findOne(id);
	}

	public long count() {
		return licencarepository.count();
	}

	public void deletar(Licenca entity) {
		licencarepository.delete(entity);
		logservice.salvar(entity, MenuSistema.licenca, LogAcaoUsuarioSistema.REMOVER);
	}
	
	public boolean verificaLicenca() {
		/*
		boolean validada = false;
		Licenca licenca = findOne(1L);
		dadoscliente.setCnpj(licenca.getCpfcnpj());
		dadoscliente.setIe(licenca.getIe());
		dadoscliente.setNomeFantasia(licenca.getNomefantasia());
		dadoscliente.setRazaoSocial(licenca.getRazaosocial());
		dadoscliente.setSistema("INSIS0007");
		dadoscliente = licenciamento.getLicenca(dadoscliente);
		if(licenca.getLicenca().equals(dadoscliente.getLicencafull()) || 
				licenca.getLicenca().equals(dadoscliente.getLicencamensal())) {
			validada = true;
		}
		return validada;
		*/
		return true;
	}
	

}
