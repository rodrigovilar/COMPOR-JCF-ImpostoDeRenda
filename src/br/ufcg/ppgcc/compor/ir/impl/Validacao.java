package br.ufcg.ppgcc.compor.ir.impl;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;

public class Validacao {
	
	private static final String MASCARA_CPF = "(^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2})$";

	public static void obrigatorio(String valor, String mensagem) {
		if (valor == null || valor.equals("")) {
			throw new ExcecaoImpostoDeRenda(mensagem);
		}
	}
	
	public static void cpf(String cpf, String mensagem){
		if (!cpf.matches(MASCARA_CPF)) {
			throw new ExcecaoImpostoDeRenda(mensagem);			
		}
	}

}
