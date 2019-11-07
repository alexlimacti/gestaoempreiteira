package com.indeas.gestaoempreiteira.controle;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.indeas.gestaoempreiteira.servico.LicencaService;
import com.indeas.gestaoempreiteira.servico.PermissaoService;

@Controller
@RequestMapping("/home")
public class DashboardController {
	
	public static final String DASHBOARD = "dashboard/home";
	public static final String LOGIN = "custom-login";
	
	@Autowired
	private PermissaoService permissaoService;
	
	@Autowired
	private LicencaService licencaservice;

	@RequestMapping("")
	public ModelAndView montandoMenu(@AuthenticationPrincipal User user) throws IOException {
		ModelAndView mv = new ModelAndView();
		if(user == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(DASHBOARD);
			mv.addObject("usuarioDashboardSeguranca",permissaoService.getNomeUsuario());
			mv.addObject("permissoes", permissaoService.getPermissoes());
			mv.addObject("licenca",licencaservice.findOne(1L));
		}
		return mv;
	}

}
