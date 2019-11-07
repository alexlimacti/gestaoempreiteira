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
import com.indeas.gestaoempreiteira.modelo.TipoEquipamento;
import com.indeas.gestaoempreiteira.modelo.VeiculoTipo;
import com.indeas.gestaoempreiteira.repositorio.filtro.VeiculoTipoFilter;
import com.indeas.gestaoempreiteira.servico.MarcaService;
import com.indeas.gestaoempreiteira.servico.PermissaoService;
import com.indeas.gestaoempreiteira.servico.VeiculoTipoService;
import com.indeas.gestaoempreiteira.servico.excecoes.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/veiculotipo")
public class VeiculoTipoController {
	
	public static final String LOGIN = "custom-login";
	public static final String CADASTRO_VIEW = "veiculotipo/cadastroVeiculoTipo";
	public static final String PESQUISA_VIEW = "veiculotipo/pesquisaVeiculoTipo";

	@Autowired
	private PermissaoService permissaoservice;

	@Autowired
	private VeiculoTipoService veiculotiposervice;

	@Autowired
	private MarcaService marcaservice;

	@Autowired
	private InstanciaUsuario iu;

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") VeiculoTipoFilter filtro) {
		ModelAndView mv = new ModelAndView();
		if (iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(PESQUISA_VIEW);
			mv.addObject("usuarioDashboardSeguranca", permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			
			if (filtro.getCodigo() != null) {
				mv.addObject("veiculostipo", veiculotiposervice.findByCodigo(filtro.getCodigo()));
			} else {
				mv.addObject("veiculostipo", veiculotiposervice.findAll());
			}

		}
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(VeiculoTipo veiculotipo) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(CADASTRO_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject("marcas", marcaservice.findAll());
			mv.addObject("tipos", TipoEquipamento.values());
			mv.addObject("veiculotipo", veiculotipo);
		}
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") VeiculoTipo veiculotipo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(CADASTRO_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject("marcas", marcaservice.findAll());
			mv.addObject("tipos", TipoEquipamento.values());
			mv.addObject("veiculotipo", veiculotipo);
		}
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView salvar (@Validated VeiculoTipo veiculotipo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(veiculotipo);
		}
		if(veiculotipo.getId() == null) {
			veiculotipo.setCodigo("MD" + String.format("%05d", veiculotiposervice.count() + 1));
		}
		veiculotiposervice.save(veiculotipo);
		attributes.addFlashAttribute("mensagem", "Item salvo com sucesso!");
		return new ModelAndView("redirect:/veiculotipo/novo");
	}
	
	@RequestMapping(value = "/excluir", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam(name="id")String id) {
		veiculotiposervice.delete(veiculotiposervice.findOne(Long.valueOf(id)));
		return new ModelAndView("redirect:/veiculotipo");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") VeiculoTipo veiculotipo) {
		try {
			veiculotiposervice.delete(veiculotipo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
}
