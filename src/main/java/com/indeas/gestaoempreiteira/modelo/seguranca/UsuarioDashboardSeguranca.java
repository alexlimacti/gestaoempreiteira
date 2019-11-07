package com.indeas.gestaoempreiteira.modelo.seguranca;

public class UsuarioDashboardSeguranca {

	/*
	 * Nome do susuário
	 */
	private String nome;
	private byte[] logo;
	private int protocolosrecebidosemtramite;
	private int protocolosenviadosrecusados;
	private int protocolosenviadosrecebidos;
	private int protocolosgeral;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[]	 logo) {
		this.logo = logo;
	}

	public int getProtocolosrecebidosemtramite() {
		return protocolosrecebidosemtramite;
	}

	public void setProtocolosrecebidosemtramite(int protocolosrecebidosemtramite) {
		this.protocolosrecebidosemtramite = protocolosrecebidosemtramite;
	}

	public int getProtocolosenviadosrecusados() {
		return protocolosenviadosrecusados;
	}

	public void setProtocolosenviadosrecusados(int protocolosenviadosrecusados) {
		this.protocolosenviadosrecusados = protocolosenviadosrecusados;
	}

	public int getProtocolosenviadosrecebidos() {
		return protocolosenviadosrecebidos;
	}

	public void setProtocolosenviadosrecebidos(int protocolosenviadosrecebidos) {
		this.protocolosenviadosrecebidos = protocolosenviadosrecebidos;
	}

	public int getProtocolosgeral() {
		return protocolosgeral;
	}

	public void setProtocolosgeral(int protocolosgeral) {
		this.protocolosgeral = protocolosgeral;
	}

}
