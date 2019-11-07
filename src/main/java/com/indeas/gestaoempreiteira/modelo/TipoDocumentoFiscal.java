package com.indeas.gestaoempreiteira.modelo;

public enum TipoDocumentoFiscal {
	entrada("Documento de Entrada"),
	saida("Documento de Saída"),
	;
	
	private final String nome;
	
	private TipoDocumentoFiscal(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
	    return this.nome;
	}

	public String getNome() {
		return nome;
	}
}
