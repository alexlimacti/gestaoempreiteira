package com.indeas.gestaoempreiteira.modelo;

public enum TipoDocumento {

	DOCUMENTO("Documento"),
	DOCUMENTO_FISCAL("Documento Fiscal"),
	;
	
	private final String nome;
	
	private TipoDocumento(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public String toString() {
	    return this.nome;
	}
}
