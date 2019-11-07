package com.indeas.gestaoempreiteira.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indeas.gestaoempreiteira.modelo.CatalogoMateriais;
import com.indeas.gestaoempreiteira.servico.CatalogoMateriaisService;

@Controller
@RequestMapping("/catalogomateriais")
public class ControleMateriaisController {
	
	@Autowired private CatalogoMateriaisService catalogomateriaisservice;
	
	@GetMapping("/localizar")
	public @ResponseBody List<CatalogoMateriais> pesquisar(String descricao) {
		return catalogomateriaisservice.findByDescricaoStartingWithIgnoreCase(descricao); 
	}

}
