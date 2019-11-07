package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.PneuTipo;

public interface PneuTipoRepository extends JpaRepository<PneuTipo, Long> {
	
	public PneuTipo findBySigla(String Sigla);	
	
	//@Query("select p from PneuTipo p where p.sigla like %?0%")
	public List<PneuTipo> findByDescricaoContaining(String descricao);
}
