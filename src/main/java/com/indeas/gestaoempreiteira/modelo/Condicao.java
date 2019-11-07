package com.indeas.gestaoempreiteira.modelo;

public enum Condicao {
	
	SIM("Sim"),
	NAO("Não");
	
	private String descricao;
	
	private Condicao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
	
}
