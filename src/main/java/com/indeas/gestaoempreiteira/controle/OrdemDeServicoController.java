package com.indeas.gestaoempreiteira.controle;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.indeas.gestaoempreiteira.config.InstanciaUsuario;
import com.indeas.gestaoempreiteira.modelo.CatalogoMateriais;
import com.indeas.gestaoempreiteira.modelo.OrdemServico;
import com.indeas.gestaoempreiteira.modelo.Servico;
import com.indeas.gestaoempreiteira.modelo.Status;
import com.indeas.gestaoempreiteira.modelo.TipoManutencao;
import com.indeas.gestaoempreiteira.repositorio.filtro.OsFilter;
import com.indeas.gestaoempreiteira.servico.CatalogoMateriaisService;
import com.indeas.gestaoempreiteira.servico.OrdemServicoService;
import com.indeas.gestaoempreiteira.servico.PermissaoService;
import com.indeas.gestaoempreiteira.servico.ServicoService;
import com.indeas.gestaoempreiteira.servico.VeiculoService;
import com.indeas.gestaoempreiteira.sessao.ItensOrdemServico;

@Controller
@RequestMapping("/os")
public class OrdemDeServicoController {

	public static final String LOGIN = "custom-login";
	public static final String CADASTRO_VIEW = "ordemservico/cadastroOrdemservico";
	public static final String PESQUISA_VIEW = "ordemservico/pesquisaOrdemservico";
	public static final String TABELA_SERVICOS = "ordemservico/tabelaservicos";

	@Autowired
	private InstanciaUsuario iu;

	@Autowired
	private PermissaoService permissaoservice;

	@Autowired
	private OrdemServicoService ordemservicoservice;

	@Autowired
	private VeiculoService veiculoservice;

	@Autowired
	private ServicoService servicoservice;

	@Autowired
	private CatalogoMateriaisService catalogomateriaisservice;

	@Autowired
	private ItensOrdemServico itens;

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") OsFilter filtro) {
		ModelAndView mv = new ModelAndView();
		if (iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(PESQUISA_VIEW);
			mv.addObject("usuarioDashboardSeguranca", permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());
			if (filtro.getUsuariosolicitante() != null) {
				mv.addObject("ordens", ordemservicoservice.findByUsuariosolicitante(filtro.getUsuariosolicitante()));
			} else {
				mv.addObject("ordens", ordemservicoservice.findAll());
			}

		}
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novo(OrdemServico os) {
		ModelAndView mv = new ModelAndView();
		if (iu.getUsuario() == null) {
			mv.setViewName(LOGIN);
		} else {
			mv.setViewName(CADASTRO_VIEW);
			mv.addObject("usuarioDashboardSeguranca", permissaoservice.getNomeUsuario());
			mv.addObject("permissoes", permissaoservice.getPermissoes());

			if (os.isNovo()) {
				os.setCodigo("Código Sendo Gerado");
				itens = new ItensOrdemServico();
			}
			
			//itens.setCatalogomateriais(os.getMateriais());
			//itens.setServicos(os.getServicos());
			mv.addObject("listaServicos", itens.getServicos());
			mv.addObject("listaMateriais", itens.getCatalogomateriais());
			mv.addObject("tipomanutencoes", TipoManutencao.values());
			mv.addObject("equipamentos", veiculoservice.findByDisponivel(true));
			mv.addObject("os", os);

		}
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated @ModelAttribute("os")OrdemServico ordemServico, BindingResult result, Model model, RedirectAttributes attributes) {
		System.out.println(ordemServico.getVeiculo());
		
		ordemServico.setServicos(itens.getServicos());
		ordemServico.setMateriais(itens.getCatalogomateriais());
		if (result.hasErrors()) {
			return novo(ordemServico);
		}
		
		if(ordemServico.isNovo()) {
			ordemServico.setCodigo("OS" + String.format("%08d", ordemservicoservice.count() + 1));
		}
		ordemServico.setFinalizado(false);
		ordemServico.setCancelado(false);
		ordemServico.setUsuariosolicitante(iu.getUsuario());
		ordemServico.setDataos(new Date());
		ordemServico.setStatus(Status.aguardandoatendimento);
		ordemservicoservice.salvar(ordemServico);
		attributes.addFlashAttribute("mensagem", "OS salva com sucesso!");
		return new ModelAndView("redirect:/os/novo");
	}

	@RequestMapping("/editar")
	public ModelAndView edicao(@RequestParam("codigo") String codigo) {
		ModelAndView mv = this.novo(ordemservicoservice.findByCodigo(codigo));
		return mv;
	}
	
	
	
	/*
	 * Itens da OS
	 */
	
	@PostMapping(value="/adicionarmaterial")
	public @ResponseBody ModelAndView adicionarProduto(String id){	
		CatalogoMateriais produto = catalogomateriaisservice.findOne(Long.parseLong(id));		
		itens.adicionarCatalogoMateriais(produto);				
		ModelAndView mv = new ModelAndView("ordemservico/tabelaitens");
		mv.addObject("listaMateriais",itens.getCatalogomateriais());		
		return mv;
	}
	
	@GetMapping(value="/adicionarservico")
	public @ResponseBody ModelAndView adicionarServico(String id){
		Servico servico = servicoservice.findOne(Long.parseLong(id));
		boolean existe = false;
		for(Servico s:itens.getServicos()) {
			if(s.getCodigo().equals(servico.getCodigo())) {
				existe = true;
			}
		}
		if(!existe) {
			itens.adicionarServico(servico);
		}
		ModelAndView mv = new ModelAndView(TABELA_SERVICOS);
		mv.addObject("listaServicos",itens.getServicos());		
		return mv;
	}
	
	@PostMapping(value="/excluirmaterial")
	public @ResponseBody ModelAndView excluirPoduto(String id){
		CatalogoMateriais produto = catalogomateriaisservice.findOne(Long.parseLong(id));
		ModelAndView mv = new ModelAndView("ordemservico/tabelaservicos");			
		itens.excluirCatalogoMateriais(produto);		
		mv.addObject("listaProdutos",itens.getCatalogomateriais());		
		return mv;
	}
	
	@GetMapping(value="/excluirservico")
	public @ResponseBody ModelAndView excluirServico(String id){
		Servico servico = servicoservice.findOne(Long.valueOf(id));
		ModelAndView mv = new ModelAndView(TABELA_SERVICOS);				
		itens.excluirServico(servico);
		mv.addObject("listaServicos",itens.getServicos());
		return mv;
	}
	
	/*
	 * Ação da OS
	 */
	
	@PostMapping(value = "/nova", params = "emitir")
	public ModelAndView emitir(OrdemServico ordemServico, BindingResult result, RedirectAttributes attributes) {
		/*
		 * Alterar o status da OS para Aguardando atendimento
		 */
		attributes.addFlashAttribute("mensagem", "Venda emitida com sucesso");
		return new ModelAndView("redirect:/vendas/nova");
	}
	
	@PostMapping(value = "/nova", params = "liberarImprimirEmail")
	public ModelAndView liberarImprimirEmail(OrdemServico ordemServico, BindingResult result, RedirectAttributes attributes) {
		
		/*
		 * Alterar o status da os para aguardando atendimento e enviar email
		 */
		
		//mailer.enviar(venda);
		
		attributes.addFlashAttribute("mensagem", String.format("OS nº %d salva com sucesso e e-mail enviado", ordemServico.getCodigo()));
		return new ModelAndView("redirect:/vendas/nova");
	}
	
	@PostMapping(value = "/nova", params = "cancelar")
	public ModelAndView cancelar(OrdemServico ordemServico, BindingResult result, RedirectAttributes attributes) {
		/*
		 * Efetuar o cancelamento da OS.
		 * 
		 * Lembrando que as OS não podem ser apagadas.
		 */
		
		/*
		try {
			cadastroVendaService.cancelar(venda);
		} catch (AccessDeniedException e) {
			return new ModelAndView("/403");
		}
		*/
		attributes.addFlashAttribute("mensagem", "OS cancelada com sucesso");
		return new ModelAndView("redirect:/vendas/" + ordemServico.getCodigo());
	}
	
}
