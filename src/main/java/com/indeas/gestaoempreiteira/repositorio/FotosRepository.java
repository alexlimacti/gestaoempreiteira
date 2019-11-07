package com.indeas.gestaoempreiteira.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Fotos;

public interface FotosRepository extends JpaRepository<Fotos, Long> {

	public Fotos findByCodigoidentificacao(String codigo);	

}
