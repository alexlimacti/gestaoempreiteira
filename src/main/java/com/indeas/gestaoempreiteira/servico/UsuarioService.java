package com.indeas.gestaoempreiteira.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.CentroDeCusto;
import com.indeas.gestaoempreiteira.modelo.LogAcaoUsuarioSistema;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.Usuario;
import com.indeas.gestaoempreiteira.repositorio.PerfilMenuRepository;
import com.indeas.gestaoempreiteira.repositorio.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PerfilMenuRepository perfilMenuRepository;
	
	@Autowired
	LogService logService;

	public long count() {
		return usuarioRepository.count();
	}

	public void delete(Usuario arg0) {
		usuarioRepository.delete(arg0);
		logService.salvar(arg0,MenuSistema.usuario,LogAcaoUsuarioSistema.REMOVER);
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findByLoginAndHabilitado(String login, boolean habilitado) {
		return usuarioRepository.findByLoginAndHabilitado(login, habilitado);
	}

	public List<Usuario> findByCentrodecustoAndHabilitado(CentroDeCusto centrodecusto, boolean habilitado) {
		return usuarioRepository.findByCentrodecustoAndHabilitado(centrodecusto, habilitado);
	}
	
	public void salvar(Usuario usuario) {
		usuarioRepository.save(usuario);
		logService.salvar(usuario,MenuSistema.usuario,LogAcaoUsuarioSistema.SALVAR);
	}

	public List<Usuario> findByHabilitado(boolean habilitado) {
		return usuarioRepository.findByHabilitado(habilitado);
	}

	public Usuario findByEmailAndHabilitado(String login, boolean habilitado) {
		return usuarioRepository.findByEmailAndHabilitado(login, habilitado);
	}
	
	

	
}
