package com.indeas.gestaoempreiteira.sessao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.indeas.gestaoempreiteira.modelo.CatalogoMateriais;
import com.indeas.gestaoempreiteira.modelo.Servico;

@SessionScope
@Component
public class ItensOrdemServico {

	List<CatalogoMateriais> catalogomateriais = new ArrayList<>();
	List<Servico> servicos = new ArrayList<>();
	Map<Long, Integer> mapQuantidadesCatalogoMateriaiss = new HashMap<>();

	public ItensOrdemServico() {
		mapQuantidadesCatalogoMateriaiss.clear();
	}

	public void adicionarCatalogoMateriais(CatalogoMateriais produto) {

		if (mapQuantidadesCatalogoMateriaiss.get(produto.getId()) == null)
			mapQuantidadesCatalogoMateriaiss.put(produto.getId(), 0);

		mapQuantidadesCatalogoMateriaiss.put(produto.getId(),
				mapQuantidadesCatalogoMateriaiss.get(produto.getId()) + 1);
		catalogomateriais.add(produto);
	}

	public void adicionarServico(Servico servico) {
		servicos.add(servico);
	}

	public void excluirCatalogoMateriais(CatalogoMateriais produto) {

		int indice = IntStream.range(0, catalogomateriais.size()).filter(i -> catalogomateriais.get(i).equals(produto)).findAny()
				.getAsInt();

		catalogomateriais.remove(indice);
	}

	public void excluirServico(Servico servico) {
		int indice = IntStream.range(0, servicos.size()).filter(i -> servicos.get(i).equals(servico)).findAny()
				.getAsInt();

		servicos.remove(indice);
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public List<CatalogoMateriais> getCatalogomateriais() {
		return catalogomateriais;
	}

	public void setCatalogomateriais(List<CatalogoMateriais> catalogomateriais) {
		this.catalogomateriais = catalogomateriais;

		mapQuantidadesCatalogoMateriaiss.clear();
		for (CatalogoMateriais produto : this.catalogomateriais) {

			if (mapQuantidadesCatalogoMateriaiss.get(produto.getId()) == null)
				mapQuantidadesCatalogoMateriaiss.put(produto.getId(), 0);

			mapQuantidadesCatalogoMateriaiss.put(produto.getId(),
					mapQuantidadesCatalogoMateriaiss.get(produto.getId()) + 1);
		}
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Map<Long, Integer> getMapQuantidadesCatalogoMateriaiss() {
		return mapQuantidadesCatalogoMateriaiss;
	}

}
