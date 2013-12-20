package br.ufcg.ppgcc.compor.ir.fachada;

public interface NovoAuditor extends Auditor {

	void transacao(Integer id, String login, String descricao, String resultado);

}
