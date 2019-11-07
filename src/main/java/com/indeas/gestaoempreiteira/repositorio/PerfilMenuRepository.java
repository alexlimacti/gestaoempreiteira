package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.Perfil;
import com.indeas.gestaoempreiteira.modelo.PerfilMenu;

public interface PerfilMenuRepository extends JpaRepository<PerfilMenu, Long> {
	
	public List<PerfilMenu> findByPerfilmenu(Perfil perfil);

}
