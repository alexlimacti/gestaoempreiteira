package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.CatalogoMateriais;

public interface CatalogoMateriaisRepository  extends JpaRepository<CatalogoMateriais, Long> {
	
	public CatalogoMateriais findByCodigo(String codigo);
	
	public List<CatalogoMateriais> findByDescricaoStartingWithIgnoreCase(String descricao);

}
