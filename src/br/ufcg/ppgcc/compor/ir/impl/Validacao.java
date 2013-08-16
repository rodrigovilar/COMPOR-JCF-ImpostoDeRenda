package br.ufcg.ppgcc.compor.ir.impl;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;

public class Validacao {
	public static void obrigatorio(String valor, String mensagem) {
		if (valor == null || valor.equals("")) {
			throw new ExcecaoImpostoDeRenda(mensagem);
		}
	}
}
