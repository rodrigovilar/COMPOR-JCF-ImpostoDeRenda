package br.ufcg.ppgcc.compor.ir.impl;

import br.ufcg.ppgcc.compor.ir.fachada.NovoAuditor;

public class NovaLogicaAuditoria {

	private static NovaLogicaAuditoria instance;

	private int transacao = 0;
	private NovoAuditor auditor;
	private String usuarioAtual;
	private String descricaoAtual;

	public static NovaLogicaAuditoria getInstancia() {
		if (instance == null) {
			instance = new NovaLogicaAuditoria();
		}
		return instance;
	}
	
	private NovaLogicaAuditoria(){}
	

	public void limpar() {
		transacao = 0;
		auditor = null;
	}

	public void setAuditor(NovoAuditor auditor) {
		this.auditor = auditor;
	}

	public void iniciarTransacaoSemUsuario(String descricao) {
		transacao++;
		usuarioAtual = null;
		descricaoAtual = descricao;
	}

	public void iniciarTransacao(String descricao) {
		transacao++;
		usuarioAtual = LogicaAutenticacao.getInstancia().getLogado();
		descricaoAtual = descricao;
	}

	public void abortarTransacao(String erro) {
		if (LogicaAutenticacao.getInstancia().isUsarAutenticacao() && auditor != null) {
			auditor.transacao(transacao, usuarioAtual, descricaoAtual, erro);
		}
	}

	public void concluirTransacao() {
		if (LogicaAutenticacao.getInstancia().isUsarAutenticacao() && auditor != null) {
			auditor.transacao(transacao, usuarioAtual, descricaoAtual, "Ok");
		}
	}
	
	
}
