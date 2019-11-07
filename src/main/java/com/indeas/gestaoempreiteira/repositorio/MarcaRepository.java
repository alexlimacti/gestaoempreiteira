package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.indeas.gestaoempreiteira.modelo.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

	public Marca findByDescricao(String descricao);
	
	@Query("select m from Marca m where"
			+ " m.descricao like %?1%")
	public List<Marca> findByDescricaoContaining(String descricao);

}
