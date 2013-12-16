package br.ufcg.ppgcc.compor.ir.impl;

import java.util.HashMap;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;

public class LogicaAutenticacao {

	private static LogicaAutenticacao instance;

	private Map<String,String> logins = new HashMap<String, String>();
	private boolean logado = false;
	private boolean usarAutenticacao = false;

	public static LogicaAutenticacao getInstancia() {
		if (instance == null) {
			instance = new LogicaAutenticacao();
		}
		return instance;
	}
	
	private LogicaAutenticacao(){}
	
	public void setUsarAutenticacao(boolean usarAutenticacao) {
		this.usarAutenticacao = usarAutenticacao;
		
	}
	
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
		
		logado = true;
	}

	public void limpar() {
		logins.clear();
		logins.put("admin", "admin");
		logado = false;
		usarAutenticacao = false;
	}
	
	public void verificarLogin() {
		if (usarAutenticacao && !logado) {
			throw new ExcecaoImpostoDeRenda("Usuário não logado");
		}
	}

	public void criarUsuario(String login, String senha) {
		verificarLogin();
		logins.put(login, senha);
	}
}
