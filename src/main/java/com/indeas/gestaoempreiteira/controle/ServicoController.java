package com.indeas.gestaoempreiteira.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.indeas.gestaoempreiteira.config.InstanciaUsuario;
import com.indeas.gestaoempreiteira.modelo.Servico;
import com.indeas.gestaoempreiteira.modelo.TipoServico;
import com.indeas.gestaoempreiteira.repositorio.filtro.ServicoFilter;
import com.indeas.gestaoempreiteira.servico.PermissaoService;
import com.indeas.gestaoempreiteira.servico.ServicoService;
import com.indeas.gestaoempreiteira.servico.excecoes.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/servicos")
public class ServicoController {
	
	public static final String LOGIN = "custom-login";
	public static final String CADASTRO_VIEW = "servico/cadastroServico";	
	public static final String PESQUISA_VIEW = "servico/pesquisaServico";
	
	@Autowired
	private PermissaoService permissaoservice;
	
	@Autowired
	private ServicoService servicoservice;
	
	@Autowired
	private InstanciaUsuario iu;
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro")ServicoFilter filtro) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(PESQUISA_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			if(filtro.getDescricao() != null) {
				mv.addObject("servicos",servicoservice.findByDescricaoContaining(filtro.getDescricao()));
			} else {
				mv.addObject("servicos",servicoservice.findAll());
			}
			
		}
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Servico servico) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(CADASTRO_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject("tiposervicos", TipoServico.values());
			mv.addObject(servico);
		}
		return mv;
	}
	
	@RequestMapping("/editar")
	public ModelAndView edicao(@RequestParam(name="codigo") String codigo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
		mv.addObject("permissoes", permissaoservice.getPermissoes());
		mv.addObject("tiposervicos", TipoServico.values());
		mv.addObject(servicoservice.findByCodigo(codigo));
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView salvar (@Validated Servico servico, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(servico);
		}
		if(servico.getId() == null) {
			servico.setCodigo("SE" + String.format("%05d", servicoservice.count() + 1));
		}
		servicoservice.salvar(servico);
		attributes.addFlashAttribute("mensagem", "Item salvo com sucesso!");
		return new ModelAndView("redirect:/servicos/novo");
	}
	
	@RequestMapping(value = "/excluir", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam(name="id")String id) {
		servicoservice.delete(servicoservice.findOne(Long.valueOf(id)));
		return new ModelAndView("redirect:/servicos");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Servico servico) {
		try {
			servicoservice.delete(servico);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	//@RequestMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/localiza")
	public @ResponseBody List<Servico> pesquisar(String descricao) {
		return servicoservice.findByDescricaoContaining(descricao); 
	}
	

}
