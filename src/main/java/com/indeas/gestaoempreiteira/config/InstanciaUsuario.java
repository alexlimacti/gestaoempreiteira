package com.indeas.gestaoempreiteira.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.Usuario;
import com.indeas.gestaoempreiteira.servico.UsuarioService;

@Service
public class InstanciaUsuario {
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Usuario getUsuario() {
		Usuario usuario;
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();	
		if(loggedInUser == null) {
			usuario = null;
		} else {
			usuario = usuarioService.findByEmailAndHabilitado(loggedInUser.getName(),true);
		}
		return usuario;
	}

}
