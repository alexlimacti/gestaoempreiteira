package com.indeas.gestaoempreiteira.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indeas.gestaoempreiteira.modelo.CentroDeCusto;
import com.indeas.gestaoempreiteira.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Usuario findByLoginAndHabilitado(String login, boolean habilitado);
	
	public Usuario findByEmailAndHabilitado(String login, boolean habilitado);
	
	public List<Usuario> findByCentrodecustoAndHabilitado(CentroDeCusto centrodecusto, boolean habilitado);
	
	public List<Usuario> findByHabilitado(boolean habilitado);

}
