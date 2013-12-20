package br.ufcg.ppgcc.compor.ir.impl;

import br.ufcg.ppgcc.compor.ir.fachada.Auditor;

public class LogicaAuditoria {

	private static LogicaAuditoria instance;

	private int transacao = 0;
	private Auditor auditor;

	public static LogicaAuditoria getInstancia() {
		if (instance == null) {
			instance = new LogicaAuditoria();
		}
		return instance;
	}
	
	private LogicaAuditoria(){}
	

	public void limpar() {
		transacao = 0;
		auditor = null;
	}

	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	public void iniciarTransacaoSemUsuario(String descricao) {
		
		if (LogicaAutenticacao.getInstancia().isUsarAutenticacao() && auditor != null) {
			
			auditor.iniciandoTransacao(++transacao, null, descricao);
		}
	}

	public void iniciarTransacao(String descricao) {
		
		if (LogicaAutenticacao.getInstancia().isUsarAutenticacao() && auditor != null) {
			
			String logado = LogicaAutenticacao.getInstancia().getLogado();
			auditor.iniciandoTransacao(++transacao, logado, descricao);
		}
	}

	public void abortarTransacao(String erro) {
		if (LogicaAutenticacao.getInstancia().isUsarAutenticacao() && auditor != null) {
			
			auditor.transacaoAbortada(transacao, erro);
		}
	}

	public void concluirTransacao() {
		if (LogicaAutenticacao.getInstancia().isUsarAutenticacao() && auditor != null) {
			
			auditor.transacaoConcluida(transacao);
		}
	}
	
	
}
