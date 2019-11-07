package com.indeas.gestaoempreiteira.repositorio.filtro;

import com.indeas.gestaoempreiteira.modelo.VeiculoTipo;

public class VeiculoFilter {

	private boolean disponivel;
	private String codigo;
	private VeiculoTipo veiculotipo;

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public VeiculoTipo getVeiculotipo() {
		return veiculotipo;
	}

	public void setVeiculotipo(VeiculoTipo veiculotipo) {
		this.veiculotipo = veiculotipo;
	}

}
