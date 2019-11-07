package com.indeas.gestaoempreiteira.controle;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.indeas.gestaoempreiteira.config.InstanciaUsuario;
import com.indeas.gestaoempreiteira.modelo.Marca;
import com.indeas.gestaoempreiteira.repositorio.filtro.MarcaFilter;
import com.indeas.gestaoempreiteira.servico.MarcaService;
import com.indeas.gestaoempreiteira.servico.PermissaoService;
import com.indeas.gestaoempreiteira.servico.excecoes.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/marca")
public class MarcaController {
	
	public static final String LOGIN = "custom-login";
	public static final String CADASTRO_VIEW = "marca/cadastroMarca";	
	public static final String PESQUISA_VIEW = "marca/pesquisaMarca";
		
	@Autowired
	private PermissaoService permissaoservice;
	
	@Autowired
	private MarcaService marcaervice;
	
	@Autowired
	private InstanciaUsuario iu;
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro")MarcaFilter filtro) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(PESQUISA_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());			
			if(filtro.getDescricao() != null) {
				mv.addObject("marcas",marcaervice.findByDescricaoContaining(filtro.getDescricao()));
			} else {
				mv.addObject("marcas",marcaervice.findAll());
			}
			
		}
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Marca marca) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(CADASTRO_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject(marca);
		}
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Marca marca) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(marca);
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar (@Validated Marca marca, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(marca);
		}
		marcaervice.salvar(marca);
		attributes.addFlashAttribute("mensagem", "Item salvo com sucesso!");
		return new ModelAndView("redirect:/marca/novo");
	}
	
	@RequestMapping(value = "/cadastrorapido", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvarr(@RequestBody @Valid Marca marca, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}		
		marcaervice.salvar(marca);
		return ResponseEntity.ok(marca);
	}
	
	@RequestMapping(value = "/excluir", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam(name="id")String id) {
		marcaervice.deletar(marcaervice.findOne(Long.valueOf(id)));
		return new ModelAndView("redirect:/marca");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Marca marca) {
		try {
			marcaervice.deletar(marca);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
}
