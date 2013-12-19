package br.ufcg.ppgcc.compor.ir.teste;

import static org.mockito.Mockito.verify;
import br.ufcg.ppgcc.compor.ir.fachada.Auditor;

public class AuditoriaHelper {

	static void verificarLoginDefaultOk(Auditor auditor, Integer transacao) {
		verify(auditor).iniciandoTransacao(transacao, null, "Login default");
		verify(auditor).transacaoConcluida(transacao);
	}

	static void verificarLoginOk(Auditor auditor, Integer transacao,
			String usuario) {
		verify(auditor).iniciandoTransacao(transacao, null, "Login " + usuario);
		verify(auditor).transacaoConcluida(transacao);
	}

	static void verificarCriacaoUsuarioOk(Auditor auditor, Integer transacao,
			String usuarioLogado, String usuarioCriado) {
		verify(auditor).iniciandoTransacao(transacao, usuarioLogado,
				"Criação do usuário " + usuarioCriado);
		verify(auditor).transacaoConcluida(transacao);
	}

	static void verificarCriacaoTitularOk(Auditor auditor, Integer transacao,
			String usuarioLogado, String titularCriado) {
		verify(auditor).iniciandoTransacao(transacao, usuarioLogado,
				"Criação do titular " + titularCriado);
		verify(auditor).transacaoConcluida(transacao);
	}

	static void verificarCriacaoFontePagadoraOk(Auditor auditor, Integer transacao,
			String usuarioLogado, String fontePagadoraCriada) {
		verify(auditor).iniciandoTransacao(transacao, usuarioLogado,
				"Criação da fonte pagadora " + fontePagadoraCriada);
		verify(auditor).transacaoConcluida(transacao);
	}

	static void verificarCriacaoDependenteOk(Auditor auditor, Integer transacao,
			String usuarioLogado, String dependenteCriado) {
		verify(auditor).iniciandoTransacao(transacao, usuarioLogado,
				"Criação do dependente " + dependenteCriado);
		verify(auditor).transacaoConcluida(transacao);
	}

	static void verificarCriacaoGastoOk(Auditor auditor, Integer transacao,
			String usuarioLogado, String gastoCriado) {
		verify(auditor).iniciandoTransacao(transacao, usuarioLogado,
				"Criação do gasto " + gastoCriado);
		verify(auditor).transacaoConcluida(transacao);
	}
}
