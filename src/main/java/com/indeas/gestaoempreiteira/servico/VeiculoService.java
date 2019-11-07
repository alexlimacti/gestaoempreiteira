package com.indeas.gestaoempreiteira.servico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.TipoEquipamento;
import com.indeas.gestaoempreiteira.modelo.Veiculo;
import com.indeas.gestaoempreiteira.modelo.VeiculoTipo;
import com.indeas.gestaoempreiteira.repositorio.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository veiculorepository;
	
	@Autowired
	private LogService logservice;

	public Veiculo findByChassis(String chassis) {
		return veiculorepository.findByChassis(chassis);
	}

	public Veiculo findByCodigo(String codigo) {
		return veiculorepository.findByCodigo(codigo);
	}

	public Veiculo findByPlaca(String placa) {
		return veiculorepository.findByPlaca(placa);
	}

	public List<Veiculo> findByQuilometragem(Long quilometrageminicial, Long quilometragemfinal) {
		return veiculorepository.findByQuilometragem(quilometrageminicial, quilometragemfinal);
	}

	public List<Veiculo> findByUltimarevisao(Date ultimarevisao) {
		return veiculorepository.findByUltimarevisao(ultimarevisao);
	}

	public List<Veiculo> findByUltimarevisao(Date datainicial, Date datafinal) {
		return veiculorepository.findByUltimarevisao(datainicial, datafinal);
	}

	public void salvar(Veiculo entity) {
		veiculorepository.save(entity);
		logservice.salvar(entity, MenuSistema.veiculo, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<Veiculo> findByDisponivel(boolean disponivel) {
		return veiculorepository.findByDisponivel(disponivel);
	}

	public List<Veiculo> findByManutencao(boolean manutencao) {
		return veiculorepository.findByManutencao(manutencao);
	}

	public List<Veiculo> findAll() {
		return veiculorepository.findAll();
	}

	public Page<Veiculo> findAll(Pageable pageable) {
		return veiculorepository.findAll(pageable);
	}

	public Veiculo findOne(Long id) {
		return veiculorepository.findOne(id);
	}

	public long count() {
		return veiculorepository.count();
	}

	public void deletar(Veiculo entity) {
		veiculorepository.delete(entity);
		logservice.salvar(entity, MenuSistema.veiculo, LogAcaoUsuarioSistema.REMOVER);
	}

	public int countByVeiculotipo(VeiculoTipo veiculotipo) {
		return veiculorepository.countByVeiculotipo(veiculotipo);
	}

	public int countByTipoequipamento(TipoEquipamento tipoequipamento) {
		return veiculorepository.countByTipoequipamento(tipoequipamento);
	}

	public List<Veiculo> findByAtivo(boolean ativo) {
		return veiculorepository.findByAtivo(ativo);
	}

	public List<Veiculo> findByCodigoStartingWithIgnoreCase(String codigo) {
		return veiculorepository.findByCodigoStartingWithIgnoreCase(codigo);
	}	

}
