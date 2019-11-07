package com.indeas.gestaoempreiteira.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.modelo.PerfilMenu;
import com.indeas.gestaoempreiteira.modelo.Usuario;
import com.indeas.gestaoempreiteira.repositorio.PerfilMenuRepository;
import com.indeas.gestaoempreiteira.servico.UsuarioService;

@Service
public class MyAppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioService userInfoDAO;
	
	@Autowired
	private PerfilMenuRepository perfilMenuRepository;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {		
		Usuario activeUserInfo = userInfoDAO.findByEmailAndHabilitado(userName, true);
		UserDetails userDetails = (UserDetails) new User(activeUserInfo.getEmail(), activeUserInfo.getSenha(), getPermissoes(activeUserInfo));
		return userDetails;
	}
	
	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {		
		List<String>menu = new ArrayList<String>();
		List<PerfilMenu>lstperfilmenu = new ArrayList<PerfilMenu>(perfilMenuRepository.findByPerfilmenu(usuario.getPerfil()));
		
		for(PerfilMenu pm:lstperfilmenu) {
			menu.add(pm.getMenu().name());
		}
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		menu.forEach(p -> authorities.add(new SimpleGrantedAuthority(p)));
		return authorities;
	}

}
