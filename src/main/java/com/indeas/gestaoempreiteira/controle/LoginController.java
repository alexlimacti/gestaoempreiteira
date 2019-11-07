package com.indeas.gestaoempreiteira.controle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.indeas.gestaoempreiteira.servico.LicencaService;

@Controller
@RequestMapping
public class LoginController {

	public static final String LOGIN = "custom-login";
	public static final String DASHBOARD = "gestaoempreiteira/home";
	public static final String LICENCA = "gestaoempreiteira/licenciamento";
	
	@Autowired
	private LicencaService licencaservice;
	
	@GetMapping("")
	public ModelAndView login(@AuthenticationPrincipal User user) {
		if(user == null) {
			return new ModelAndView(LOGIN);
		} else {
			if(licencaservice.verificaLicenca()) {
				return new ModelAndView("redirect:" + DASHBOARD);
			} else {
				return new ModelAndView("redirect:" + LICENCA);
			}
		}
	}

	@GetMapping("/403")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		mav.setViewName(LOGIN);
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView sair(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    ModelAndView mv = new ModelAndView(LOGIN);
		return mv;
	}

}
