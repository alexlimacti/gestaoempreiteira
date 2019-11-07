package com.indeas.gestaoempreiteira.modelo;

public enum Status {
	/*
	 * Genérico
	 */
	
	aguardandoautorizacao("Aguardando Autorização", "Generico"),
	autorizadoaguardandoatendimento("Autorizado e Aguardando Atendimento", "Generico"),
	autorizado("Autorizado", "Generico"),
	emaberto("Em Aberto", "Generico"),
	eminteracao("Em Interação", "Generico"),
	parcialmentefinalizado("Parcialmente Finalizado", "Generico"),
	encerrado("Encerrado", "Generico"),
	cancelado("Cancelado", "Generico"),
	entregue("Entregue", "Generico"),
	
	/*
	 * Chamados e solicitações
	 */
	
	aguardandoatendimento("Aguardando Atendimento", "Indeas"),
	emanalise("Em Análise", "Indeas"),
	emmanutencao("Em Manutenção", "Indeas"),	
	encaminhado("Encaminhado", "Indeas"),	
	devolvido("Devolvido", "Indeas"),
	
	/*
	 * Protocolo
	 */
	
	arquivado("Arquivado", "Protocolo"),
	autorizadopelogestor("Autorizado pelo gestor", "Protocolo"),
	autorizadopeladiretoria("Autorizado pela diretoria", "Protocolo"),
	aguardandoaautorizacaogestor("Aguardando autorização do gestor", "Protocolo"),
	aguardandoaautorizacaodiretoria("Aguardando autorização da diretoria", "Protocolo"),
	aguardandoenvio("Aguardando envio", "Protocolo"),
	naoaautorizadogestor("Não autorizado pelo gestor", "Protocolo"),
	naoaautorizadodiretoria("Não autorizado pela diretoria", "Protocolo"),
	aguardandoaceitacao("Aguardando aceitação", "Protocolo"),
	emprocesso("Em processo", "Protocolo"),
	emtramite("Em Trâmite", "Protocolo"),
	recebido("Recebido","protocolo"),
	recusado("Recusado", "protocolo"),
	retornoporfaltadeassinatura("Retorno por falta de assinatura", "Protocolo"),
	retornoporerrodecadastro("Retorno por erro de cadastro", "Protocolo"),
	visualizado("Protocolo Visualizado", "Protocolo"),
	
	/*
	 * Notícias
	 */
	
	ativo("Ativo", "Noticias"),
	vencida("Vencida","Noticias"),
	pendente("Pendente", "Noticias"),
	;
	
	private final String nome;
	private final String tipo;
	
	private Status(String nome, String tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}
	
	public String getTipo(){
		return tipo;
	}
	
	public String toString() {
	    return this.nome;
	}
}
