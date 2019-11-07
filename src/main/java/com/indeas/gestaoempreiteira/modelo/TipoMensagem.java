package com.indeas.gestaoempreiteira.modelo;

public enum TipoMensagem {
	Sucesso("Sucesso"),
	Erro("Erro") ,
	Alerta("Alerta"),
	Informacao("Informação"),
	Notificacao("Notificação"),
	;
	
	private String nome;
	
	private TipoMensagem(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}	
}
