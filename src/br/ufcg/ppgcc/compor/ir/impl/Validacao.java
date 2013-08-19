package br.ufcg.ppgcc.compor.ir.impl;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;

public class Validacao {
	
	private static final String MASCARA_CPF = "(^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2})$";
	private static final String MASCARA_CNPJ = "(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2})$";

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
	
	public static void cnpj(String cnpj, String mensagem){
		if (!cnpj.matches(MASCARA_CNPJ)) {
			throw new ExcecaoImpostoDeRenda(mensagem);			
		}
	}

	public static void cpfOuCnpj(String cpfCnpj, String mensagem){
		if (!cpfCnpj.matches(MASCARA_CNPJ) && !cpfCnpj.matches(MASCARA_CPF)) {
			throw new ExcecaoImpostoDeRenda(mensagem);			
		}
	}


	public static void numeroDifetenteZero(double numero, String mensagem){
		if (numero == 0.0) {
			throw new ExcecaoImpostoDeRenda(mensagem);			
		}
	}

	public static void numeroMaiorQueZero(double numero, String mensagem){
		if (numero <= 0.0) {
			throw new ExcecaoImpostoDeRenda(mensagem);			
		}
	}

}
