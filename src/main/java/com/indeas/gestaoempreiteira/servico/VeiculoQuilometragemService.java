package com.indeas.gestaoempreiteira.servico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.Veiculo;
import com.indeas.gestaoempreiteira.modelo.VeiculoQuilometragem;
import com.indeas.gestaoempreiteira.repositorio.VeiculoQuilometragemRepository;

@Service
public class VeiculoQuilometragemService {
	
	@Autowired
	private VeiculoQuilometragemRepository veiculoquilometragemrepository;
	
	@Autowired
	private LogService logservice;

	public List<VeiculoQuilometragem> findByVeiculo(Veiculo veiculo) {
		return veiculoquilometragemrepository.findByVeiculo(veiculo);
	}

	public List<VeiculoQuilometragem> findByVeiculoAndData(Veiculo veiculo, Date datainicial, Date datafinal) {
		return veiculoquilometragemrepository.findByVeiculoAndData(veiculo, datainicial, datafinal);
	}

	public List<VeiculoQuilometragem> findByDatacadastro(Date datacadastro) {
		return veiculoquilometragemrepository.findByDatacadastro(datacadastro);
	}

	public void salvar(VeiculoQuilometragem entity) {
		veiculoquilometragemrepository.save(entity);
		logservice.salvar(entity, MenuSistema.veiculoquilometragem, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<VeiculoQuilometragem> findAll() {
		return veiculoquilometragemrepository.findAll();
	}

	public VeiculoQuilometragem findOne(Long id) {
		return veiculoquilometragemrepository.findOne(id);
	}

	public long count() {
		return veiculoquilometragemrepository.count();
	}

	public void deletar(VeiculoQuilometragem entity) {
		veiculoquilometragemrepository.delete(entity);
		logservice.salvar(entity, MenuSistema.veiculoquilometragem, LogAcaoUsuarioSistema.REMOVER);
	}


}
