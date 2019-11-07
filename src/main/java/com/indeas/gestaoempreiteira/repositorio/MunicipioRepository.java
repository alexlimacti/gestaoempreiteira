package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indeas.gestaoempreiteira.modelo.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

	public List<Municipio> findByUf(String uf);
	
	@Query("from Municipio m where m.uf = :uf"
			+ " AND m.nome like %:nome%")
	public List<Municipio> findByUfAndNome(@Param("uf")String uf, @Param("nome")String nome);
	
	public Municipio findByCadastrounico(int cadastrounico);

}
