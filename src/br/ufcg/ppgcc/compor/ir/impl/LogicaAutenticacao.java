package br.ufcg.ppgcc.compor.ir.impl;

import java.util.HashMap;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;

public class LogicaAutenticacao {

	private static LogicaAutenticacao instance;

	private Map<String,String> logins = new HashMap<String, String>();

	public static LogicaAutenticacao getInstancia() {
		if (instance == null) {
			instance = new LogicaAutenticacao();
		}
		return instance;
	}
	
	private LogicaAutenticacao(){}
	
	public void login(String login, String senha) {
		if (!logins.containsKey(login)) {
			throw new ExcecaoImpostoDeRenda("Login desconhecido");
		}
		
		String senhaCadastrada = logins.get(login);
		if (senhaCadastrada == null) {
			if (senha != null) {
				throw new ExcecaoImpostoDeRenda("Senha errada");
			} 
		} else {
			if (!senhaCadastrada.equals(senha)) {
				throw new ExcecaoImpostoDeRenda("Senha errada");
			} 			
		}
	}

	public void limpar() {
		logins.clear();
		logins.put("admin", "admin");
	}
}
