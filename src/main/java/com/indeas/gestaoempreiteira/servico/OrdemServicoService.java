package com.indeas.gestaoempreiteira.servico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.CatalogoMateriais;
import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.OrdemServico;
import com.indeas.gestaoempreiteira.modelo.Status;
import com.indeas.gestaoempreiteira.modelo.Usuario;
import com.indeas.gestaoempreiteira.modelo.Veiculo;
import com.indeas.gestaoempreiteira.repositorio.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	
	@Autowired private OrdemServicoRepository ordemservicorepository;
	
	@Autowired private LogService logservice;
	
	@Autowired private CatalogoMateriaisService materiaisservice;

	public OrdemServico findByCodigo(String codigo) {
		return ordemservicorepository.findByCodigo(codigo);
	}

	public List<OrdemServico> findByVeiculo(Veiculo veiculo) {
		return ordemservicorepository.findByVeiculo(veiculo);
	}

	public List<OrdemServico> findByFinalizado(boolean finalizado) {
		return ordemservicorepository.findByFinalizado(finalizado);
	}

	public List<OrdemServico> findByStatus(Status status) {
		return ordemservicorepository.findByStatus(status);
	}

	public List<OrdemServico> findByStatusAndDataos(Status status, Date datainicio, Date datafim) {
		return ordemservicorepository.findByStatusAndDataos(status, datainicio, datafim);
	}

	public void salvar(OrdemServico entity) {
		if (entity.getStatus().equals(Status.encerrado)) {
			for (Long keyProduto : entity.getMapQuantidadesProdutos().keySet()) {
				Integer quantidade = entity.getMapQuantidadesProdutos().get(keyProduto);
				CatalogoMateriais materiais = materiaisservice.findOne(keyProduto);
				if (quantidade > materiais.getQuantidade()) {
					throw new RuntimeException("Produto " + materiais.getDescricao() + " não possui estoque suficiente!");
				}
				materiais.setQuantidade(materiais.getQuantidade() - quantidade);
				materiaisservice.salvar(materiais);
			}
		}
		ordemservicorepository.save(entity);
		logservice.salvar(entity, MenuSistema.ordemservico, LogAcaoUsuarioSistema.SALVAR);
	}

	public List<OrdemServico> findByVeiculoAndDataos(Veiculo veiculo, Date datainicio, Date datafim) {
		return ordemservicorepository.findByVeiculoAndDataos(veiculo, datainicio, datafim);
	}

	public List<OrdemServico> findByUsuarioresponsavelos(Usuario usuario) {
		return ordemservicorepository.findByUsuarioresponsavelos(usuario);
	}

	public List<OrdemServico> findAll() {
		return ordemservicorepository.findAll();
	}

	public OrdemServico findOne(Long id) {
		return ordemservicorepository.findOne(id);
	}

	public List<OrdemServico> findByUsuariosolicitante(Usuario usuario) {
		return ordemservicorepository.findByUsuariosolicitante(usuario);
	}

	public long count() {
		return ordemservicorepository.count();
	}

	public void delete(OrdemServico entity) {
		ordemservicorepository.delete(entity);
		logservice.salvar(entity, MenuSistema.ordemservico, LogAcaoUsuarioSistema.REMOVER);
	}
	
	

}
