package br.ufcg.ppgcc.compor.ir.impl;

import java.util.HashMap;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;

public class LogicaAutenticacao {

	private static final String MSG_USUÁRIO_NAO_LOGADO = "Usuário não logado";

	private static LogicaAutenticacao instance;

	private Map<String,String> logins = new HashMap<String, String>();
	private String logado = null;
	private boolean usarAutenticacao = false;
	private NovaLogicaAuditoria logicaAutenticacao = NovaLogicaAuditoria.getInstancia();


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
		if ("admin".equals(login)) {
			logicaAutenticacao.iniciarTransacaoSemUsuario("Login default");
			
		} else {
			logicaAutenticacao.iniciarTransacaoSemUsuario("Login " + login);			
		}
		
		
		if (!logins.containsKey(login)) {
			logicaAutenticacao.abortarTransacao("Usuário desconhecido");
			throw new ExcecaoImpostoDeRenda("Login desconhecido");
		}
		
		String senhaCadastrada = logins.get(login);
		if (senhaCadastrada == null) {
			if (senha != null) {
				logicaAutenticacao.abortarTransacao("Senha errada");
				throw new ExcecaoImpostoDeRenda("Senha errada");
			} 
		} else {
			if (!senhaCadastrada.equals(senha)) {
				logicaAutenticacao.abortarTransacao("Senha errada");
				throw new ExcecaoImpostoDeRenda("Senha errada");
			} 			
		}
		
		logado = login;
		logicaAutenticacao.concluirTransacao();
	}

	public void limpar() {
		logins.clear();
		logins.put("admin", "admin");
		logado = null;
		usarAutenticacao = false;
	}
	
	public void verificarLogin() {
		if (usarAutenticacao && logado == null) {
			logicaAutenticacao.abortarTransacao(MSG_USUÁRIO_NAO_LOGADO);
			throw new ExcecaoImpostoDeRenda(MSG_USUÁRIO_NAO_LOGADO);
		}
	}

	public void criarUsuario(String login, String senha) {
		logicaAutenticacao.iniciarTransacao("Criação do usuário " + login);			
		verificarLogin();
		logins.put(login, senha);
		logicaAutenticacao.concluirTransacao();
	}

	public boolean isUsarAutenticacao() {
		return usarAutenticacao;
	}

	public String getLogado() {
		return logado;
	}
	
	
}
