package com.indeas.gestaoempreiteira.controle;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.indeas.gestaoempreiteira.config.InstanciaUsuario;
import com.indeas.gestaoempreiteira.modelo.PneuHistorico;
import com.indeas.gestaoempreiteira.modelo.VeiculoEixos;
import com.indeas.gestaoempreiteira.repositorio.filtro.PneuHistoricoFilter;
import com.indeas.gestaoempreiteira.servico.PermissaoService;
import com.indeas.gestaoempreiteira.servico.PneuHistoricoService;
import com.indeas.gestaoempreiteira.servico.PneuService;
import com.indeas.gestaoempreiteira.servico.VeiculoService;

@Controller
@RequestMapping("/pneuhistorico")
public class PneuHistoricoController {	
	
	public static final String LOGIN = "custom-login";
	public static final String CADASTRO_VIEW = "pneuhistorico/cadastroPneuHistorico";	
	public static final String PESQUISA_VIEW = "pneuhistorico/pesquisaPneuHistorico";
	
	@Autowired
	private PneuHistoricoService pneuhistoricoservice;
	
	@Autowired
	private PneuService pneuservice;
	
	@Autowired
	private VeiculoService veiculoservice;
	
	@Autowired
	private PermissaoService permissaoservice;
	
	@Autowired
	private InstanciaUsuario iu;
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro")PneuHistoricoFilter filtro) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(PESQUISA_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			if(filtro.getPneu() != null) {
				mv.addObject("pneushistorico",pneuhistoricoservice.findByPneu(filtro.getPneu()));
			} else {
				mv.addObject("pneushistorico", new ArrayList<PneuHistorico>());
			}
		}
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(PneuHistorico pneuhistorico) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(CADASTRO_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject("pneus", pneuservice.findByInutilizavel(false));
			mv.addObject("veiculos", veiculoservice.findByAtivo(true));
			mv.addObject("eixos",VeiculoEixos.values());
			mv.addObject("pneuhistorico", pneuhistorico);
		}
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") PneuHistorico pneuhistorico) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
		mv.addObject("permissoes", permissaoservice.getPermissoes());
		mv.addObject("pneus", pneuservice.findByInutilizavel(false));
		mv.addObject("veiculos", veiculoservice.findByAtivo(true));
		mv.addObject("eixos",VeiculoEixos.values());
		mv.addObject("pneuhistorico", pneuhistorico);
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView salvar (@Validated PneuHistorico pneuhistorico, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(pneuhistorico);
		}
		pneuhistoricoservice.salvar(pneuhistorico);
		attributes.addFlashAttribute("mensagem", "Item salvo com sucesso!");
		return new ModelAndView("redirect:/gestaoempreiteira/pneuhistorico/novo");
	}
	
	@RequestMapping(value = "/delete_pneu", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam(name="id")String id) {
		pneuservice.deletar(pneuservice.getOne(Long.valueOf(id)));
		return new ModelAndView("redirect:/gestaoempreiteira/pneuhistorico");
	}

}
