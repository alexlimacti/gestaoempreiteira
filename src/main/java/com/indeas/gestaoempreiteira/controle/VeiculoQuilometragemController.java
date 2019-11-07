package com.indeas.gestaoempreiteira.controle;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.indeas.gestaoempreiteira.config.InstanciaUsuario;
import com.indeas.gestaoempreiteira.modelo.VeiculoQuilometragem;
import com.indeas.gestaoempreiteira.servico.PermissaoService;
import com.indeas.gestaoempreiteira.servico.VeiculoQuilometragemService;
import com.indeas.gestaoempreiteira.servico.VeiculoService;

@Controller
@RequestMapping("/veiculoquilometragem")
public class VeiculoQuilometragemController {

	public static final String LOGIN = "custom-login";
	public static final String CADASTRO_VIEW = "veiculoquilometragem/cadastroVeiculoQuilometragem";
	public static final String PESQUISA_VIEW = "veiculoquilometragem/pesquisaVeiculoQuilometragem";
	
	@Autowired
	private PermissaoService permissaoservice;
	
	@Autowired
	private VeiculoService veiculoservice;
	
	@Autowired
	private InstanciaUsuario iu;
	
	@Autowired
	private VeiculoQuilometragemService veiculoquilometragemservice;
	
	@RequestMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(PESQUISA_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject("veiculosquilometragem",veiculoquilometragemservice.findByDatacadastro(new Date()));
			
		}
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(VeiculoQuilometragem veiculoquilometragem) {
		ModelAndView mv = new ModelAndView();
		if(iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(CADASTRO_VIEW);
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject("equipamentos", veiculoservice.findByDisponivel(true));
			mv.addObject("veiculoquilometragem",veiculoquilometragem);
		}
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") VeiculoQuilometragem veiculoquilometragem) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
		mv.addObject("permissoes", permissaoservice.getPermissoes());
		mv.addObject("equipamentos", veiculoservice.findByDisponivel(true));
		mv.addObject("veiculoquilometragem", veiculoquilometragem);
		return mv;
	}
	
	@RequestMapping(value="/novo", method = RequestMethod.POST)
	public ModelAndView salvar (@Validated VeiculoQuilometragem veiculoquilometragem, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(veiculoquilometragem);
		}
		ModelAndView mv;
		if(veiculoquilometragem.getQuilometragemfinal() < veiculoquilometragem.getQuilometrageminicial()) {
			mv = new ModelAndView(CADASTRO_VIEW);
			mv.addObject("mensagemAviso","A quilometragem final não pode ser menor que a quilometragem inicial!");
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject("equipamentos", veiculoservice.findByDisponivel(true));
			mv.addObject("veiculoquilometragem", veiculoquilometragem);
			return mv;
		} else if(!veiculoquilometragemservice.findByVeiculoAndData(veiculoquilometragem.getVeiculo(), veiculoquilometragem.getData(), veiculoquilometragem.getData()).isEmpty()) {
			mv = new ModelAndView(CADASTRO_VIEW);
			mv.addObject("mensagemAviso","Já existe um registro para este veículo na data especificada!");
			mv.addObject("usuarioDashboardSeguranca",permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			mv.addObject("equipamentos", veiculoservice.findByDisponivel(true));
			mv.addObject("veiculoquilometragem", veiculoquilometragem);
			return mv;
		} else {
			veiculoquilometragem.getVeiculo().setQuilometragem(veiculoquilometragem.getQuilometragemfinal());
			veiculoservice.salvar(veiculoquilometragem.getVeiculo());
			veiculoquilometragem.setDatacadastro(new Date());
			veiculoquilometragemservice.salvar(veiculoquilometragem);
			attributes.addFlashAttribute("mensagem", "Item salvo com sucesso!");
			return new ModelAndView("redirect:/veiculoquilometragem/novo");
		}
	}
	
	@RequestMapping(value = "/delete_veiculoquilometragem", method = RequestMethod.GET)
	public ModelAndView deletar(@RequestParam(name="id")String id) {
		veiculoquilometragemservice.deletar(veiculoquilometragemservice.findOne(Long.valueOf(id)));
		return new ModelAndView("redirect:/veiculoquilometragem");
	}
	
}
