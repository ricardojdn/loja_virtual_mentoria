package com.mentoria.lojavirtual.enums;

public enum StatusContaReceber {
COBRANCA("PAGAR"),
VENCIDA("Vencida"),
ABERTA("Aberta"),
QUITADA("Quitada");

	private String descricao;

	private StatusContaReceber(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return this.descricao;
	}
	
}
