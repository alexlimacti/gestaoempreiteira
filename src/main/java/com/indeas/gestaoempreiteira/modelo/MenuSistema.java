package com.indeas.gestaoempreiteira.modelo;

public enum MenuSistema {
	
	usuario("Cadastro de Usuários", "role_user"),
	perfil("Cadastro de Perfis", "role_user"),
	licenca("Gerenciamento de Licenças", "role_user"),
	centrodecusto("Cadastro de Centro de Custo", "role_user"),
	fornecedor("Cadastro de Fornecedores", "role_user"),
	documentos("Cadastro de Documentos", "role_user"),
	protocolo("Protocolo", "role_user"),
	contratos("Gestão de Contratos","role_user"),
	marca("Cadastro de Marcas","role_user"),
	unidadedemedida("Cadastro de Unidade de Medida","role_user"),
	catalogodemateriais("Catálogo de Materiais","role_user"),
	mensagensemail("Mensagens de Email", "role_admin"),
	pneu("Cadastro de Pneus", "role_user"),
	pneutipo("Cadastro de Tipo de Pneus", "role_user"),
	pneuquilometragem("Controle de Quilometragem dos Pneus","role_user"),
	pneuhistorico("Constrole de Movimentação de Pneu", "role_user"),
	veiculo("Cadastro de Veículos", "role_user"),
	veiculotipo("Cadastro de Tipo de Veículo", "role_user"),
	veiculoquilometragem("Controle de Quilometragem de Equipamento","role_user"),
	ordemservico("Abertura de Ordem de Serviços", "role_user"),
	ordemservicoatendimento("Atendimento da OS", "role_user"),
	servico("Cadastro de Serviços","role_user"),
	;
	
	private final String nome;
	private final String tipoUsuario;

	private MenuSistema(String nome, String tipoUsuario) {
		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
	}

	public String getNome() {
		return nome;
	}
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public String toString() {
		return this.nome;
	}
}
