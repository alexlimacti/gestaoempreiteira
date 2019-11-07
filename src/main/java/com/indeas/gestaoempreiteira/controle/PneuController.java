package com.indeas.gestaoempreiteira.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.indeas.gestaoempreiteira.config.InstanciaUsuario;
import com.indeas.gestaoempreiteira.modelo.Pneu;
import com.indeas.gestaoempreiteira.repositorio.filtro.PneuFilter;
import com.indeas.gestaoempreiteira.servico.MarcaService;
import com.indeas.gestaoempreiteira.servico.PermissaoService;
import com.indeas.gestaoempreiteira.servico.PneuService;
import com.indeas.gestaoempreiteira.servico.PneuTipoService;
import com.indeas.gestaoempreiteira.servico.excecoes.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/pneus")
public class PneuController {
	
	public static final String LOGIN = "custom-login";
	public static final String CADASTRO_VIEW = "pneu/cadastroPneu";	
	public static final String PESQUISA_VIEW = "pneu/pesquisaPneu";
		
	@Autowired
	private PermissaoService permissaoservice;
	
	@Autowired
	private PneuService pneuservice;
	
	@Autowired
	private MarcaService marcaservice;
	
	@Autowired
	private PneuTipoService pneutiposervice;
	
	@Autowired
	private InstanciaUsuario iu;
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro")PneuFilter filtro) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(PESQUISA_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			if(filtro.getDescricao() != null) {
				mv.addObject("pneus",pneuservice.findByModelo(filtro.getDescricao()));
			} else {
				mv.addObject("pneus",pneuservice.findAll());
			}
			
		}
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Pneu pneu) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(CADASTRO_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject("tipospneu", pneutiposervice.findAll());
			if(pneu.getCodigo() == null) {
				pneu.setCodigo("Código Sendo Gerado");
			}
			mv.addObject("marcas", marcaservice.findAll());
			mv.addObject(pneu);
		}
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Pneu pneu) {
		ModelAndView mv =  novo(pneu);
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView salvar (@Validated Pneu pneu, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(pneu);
		}
		
		if(pneu.getId() == null) {
			
			boolean existe = true;
			int codigo = pneuservice.countByPneuTipo(pneu.getTipo());
			while(existe) {
				if(pneuservice.findByCodigo(pneu.getTipo().getSigla()+String.format("%05d",codigo)) == null) {
					existe = false;
				} else {
					codigo = codigo + 1;
				}
			}
			pneu.setCodigo(pneu.getTipo().getSigla() + String.format("%05d", codigo));
		}
		pneuservice.salvar(pneu);
		attributes.addFlashAttribute("mensagem", "Item salvo com sucesso!");
		return new ModelAndView("redirect:/pneus/novo");
	}
	
	@RequestMapping(value = "/delete_pneu", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam(name="id")String id) {
		pneuservice.deletar(pneuservice.getOne(Long.valueOf(id)));
		return new ModelAndView("redirect:/pneus");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Pneu pneu) {
		try {
			pneuservice.deletar(pneu);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
}
