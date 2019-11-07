package com.indeas.gestaoempreiteira.servico;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indeas.gestaoempreiteira.config.InstanciaUsuario;
import com.indeas.gestaoempreiteira.modelo.MenuSistema;
import com.indeas.gestaoempreiteira.modelo.PerfilMenu;
import com.indeas.gestaoempreiteira.modelo.Status;
import com.indeas.gestaoempreiteira.modelo.seguranca.Permissao;
import com.indeas.gestaoempreiteira.modelo.seguranca.UsuarioDashboardSeguranca;
import com.indeas.gestaoempreiteira.repositorio.PerfilMenuRepository;

@Service
public class PermissaoService {
	
	@Autowired
	private PerfilMenuRepository perfilMenuRepository;
	
	@Autowired
	private InstanciaUsuario instanciaUsuario;
	
	@Autowired
	private ProtocoloService protocoloService;
	
	public List<Permissao>getPermissoes(){
		List<PerfilMenu>lst = new ArrayList<PerfilMenu>(perfilMenuRepository.findByPerfilmenu(instanciaUsuario.getUsuario().getPerfil()));
		List<Permissao>menu = new ArrayList<Permissao>();
		for(PerfilMenu pm:lst) {
			Permissao p = new Permissao();
			p.setMenu(pm.getMenu().name());
			menu.add(p);
		}
		return menu;
	}
	
	public UsuarioDashboardSeguranca getNomeUsuario() {
		UsuarioDashboardSeguranca p = new UsuarioDashboardSeguranca();
		p.setNome(instanciaUsuario.getUsuario().getNome());
		p.setLogo(instanciaUsuario.getUsuario().getLogo());
		p.setProtocolosenviadosrecebidos(protocoloService.countByAutorAndStatus(instanciaUsuario.getUsuario(), Status.recebido));
		p.setProtocolosenviadosrecusados(protocoloService.countByAutorAndStatus(instanciaUsuario.getUsuario(), Status.recusado));
		p.setProtocolosrecebidosemtramite(protocoloService.countByDestinatarioAndStatus(instanciaUsuario.getUsuario(), Status.emtramite));
		p.setProtocolosgeral(protocoloService.countByAutorAndStatus(instanciaUsuario.getUsuario(), null));
		return p;
	}
	
	public List<Permissao>getPermissoesBotoes(MenuSistema menuSistema){
		List<PerfilMenu>lst = new ArrayList<PerfilMenu>(perfilMenuRepository.findByPerfilmenu(instanciaUsuario.getUsuario().getPerfil()));
		List<Permissao>menu = new ArrayList<Permissao>();
		for(PerfilMenu pm:lst) {
			if(pm.getMenu().equals(menuSistema)) {
				Permissao p;
				if(pm.isSomenteleitura()) {
					p = new Permissao();
					p.setMenu("somenteleitura");
					menu.add(p);
				} else if (pm.isExcluir()) {
					p = new Permissao();
					p.setMenu("excluir");
					menu.add(p);
				} else {
					p = new Permissao();
					p.setMenu("normal");
					menu.add(p);
				}
			}
		}
		return menu;
	}
	
	public BufferedImage byteToImage(byte[] bytes) throws IOException {


			BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
			return img;
		/*
        if (bytes == null) {
            return null;
        }
        return Toolkit.getDefaultToolkit().createImage(bytes);
        */
        
    }

}
