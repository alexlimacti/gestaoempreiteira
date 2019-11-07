package com.indeas.gestaoempreiteira.modelo;

public enum VeiculoEixos {
	DE("Dianteiro Esquerdo"),
	DD("Dianteiro Direito"),
	
	TEE("Tração Esquerdo Externo"),
	TEI("Tração Esquerdo Interno"),
	TDE("Tração Direito Externo"),
	TDI("Tração Direito Interno"),
		
	LEE1("Livre Esquerdo Externo"),
	LEI1("Livre Esquerdo Interno"),
	LDE1("Livre Direito Externo"),
	LDI1("Livre Direito Interno"),
	;
	
	private String eixo;

	public String getEixo() {
		return eixo;
	}

	private VeiculoEixos(String eixo) {
		this.eixo = eixo;
	}
	
	
}
