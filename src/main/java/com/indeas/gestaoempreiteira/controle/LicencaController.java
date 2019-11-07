package com.indeas.gestaoempreiteira.controle;

import org.springframework.beans.factory.annotation.Autowired;

import com.indeas.modelo.DadosCliente;
import com.indeas.modelo.Licenca;
import com.indeas.modelo.Licenciamento;

public class LicencaController {
	
	@Autowired
	Licenca licenca;
	
	@Autowired
	Licenciamento licenciamento;

}
