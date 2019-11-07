package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

	public Servico findByCodigo(String codigo);
	
	public List<Servico> findByDescricaoStartingWithIgnoreCase(String descricao);

}
