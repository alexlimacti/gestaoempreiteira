package com.indeas.gestaoempreiteira.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.indeas.gestaoempreiteira.modelo.Veiculo;
import com.indeas.gestaoempreiteira.repositorio.filtro.VeiculoFilter;
import com.indeas.gestaoempreiteira.servico.PermissaoService;
import com.indeas.gestaoempreiteira.servico.VeiculoService;
import com.indeas.gestaoempreiteira.servico.VeiculoTipoService;
import com.indeas.gestaoempreiteira.servico.excecoes.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {

	public static final String LOGIN = "custom-login";
	public static final String CADASTRO_VIEW = "veiculo/cadastroVeiculo";
	public static final String PESQUISA_VIEW = "veiculo/pesquisaVeiculo";

	@Autowired
	private PermissaoService permissaoservice;

	@Autowired
	private VeiculoService veiculoservice;

	@Autowired
	private VeiculoTipoService veiculotiposervice;

	@Autowired
	private InstanciaUsuario iu;

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") VeiculoFilter filtro, @PageableDefault(size=2) Pageable pageable) {
		ModelAndView mv = new ModelAndView();
		if (iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(PESQUISA_VIEW);
			mv.addObject("usuarioDashboardSeguranca", permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			if (filtro.getCodigo() != null) {
				mv.addObject("veiculos", veiculoservice.findByCodigo(filtro.getCodigo()));
			} else {
				mv.addObject("veiculos", veiculoservice.findAll());
			}
		}
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Veiculo veiculo) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(CADASTRO_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject("veiculotipo", veiculotiposervice.findAll());
			mv.addObject("veiculo", veiculo);
		}
		return mv;
	}	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Veiculo veiculo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
		mv.addObject("permissoes", permissaoservice.getPermissoes());
		mv.addObject("veiculotipo", veiculotiposervice.findAll());
		mv.addObject("veiculo", veiculo);
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView salvar (@Validated @ModelAttribute("veitulo")Veiculo veiculo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(veiculo);
		}
		veiculo.setTipoequipamento(veiculo.getTipo().getTipoequipamento());
		if(veiculo.getId() == null) {
			boolean existe = true;
			int codigo = veiculoservice.countByTipoequipamento(veiculo.getTipoequipamento());
			while(existe) {
				if(veiculoservice.findByCodigo(veiculo.getTipoequipamento().name()+String.format("%05d",codigo)) == null) {
					existe = false;
				} else {
					codigo = codigo + 1;
				}
			}
			veiculo.setCodigo(veiculo.getTipoequipamento().name() + String.format("%05d", codigo));
		}
		veiculo.setAtivo(true);
		veiculoservice.salvar(veiculo);
		attributes.addFlashAttribute("mensagem", "Item salvo com sucesso!");
		return new ModelAndView("redirect:/veiculo/novo");
	}
	
	@RequestMapping(value = "/delete_veiculo", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam(name="id")String id) {
		veiculoservice.deletar(veiculoservice.findOne(Long.valueOf(id)));
		return new ModelAndView("redirect:/veiculo");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Veiculo veiculo) {
		try {
			veiculoservice.deletar(veiculo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/localiza")
	public @ResponseBody List<Veiculo> pesquisar(String codigo) {
		return veiculoservice.findByCodigoStartingWithIgnoreCase(codigo);
	}
}
