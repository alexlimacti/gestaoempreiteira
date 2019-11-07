package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
	
	public List<Log> findByUsuariologin(String usuariologin);

}
