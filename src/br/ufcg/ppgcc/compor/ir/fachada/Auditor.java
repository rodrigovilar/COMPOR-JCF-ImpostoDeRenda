package br.ufcg.ppgcc.compor.ir.fachada;

public interface Auditor {

	void iniciandoTransacao(Integer id, String login, String mensagem);
	void transacaoConcluida(Integer id);
	void transacaoAbortada(Integer id, String erro);

}
