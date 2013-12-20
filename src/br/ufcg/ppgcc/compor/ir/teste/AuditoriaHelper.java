package br.ufcg.ppgcc.compor.ir.teste;

import static org.mockito.Mockito.verify;
import br.ufcg.ppgcc.compor.ir.fachada.NovoAuditor;

public class AuditoriaHelper {

	static void verificarLoginDefaultOk(NovoAuditor auditor, Integer transacao) {
		verify(auditor).transacao(transacao, null, "Login default", "Ok");
	}

	static void verificarLoginOk(NovoAuditor auditor, Integer transacao,
			String usuario) {
		verify(auditor).transacao(transacao, null, "Login " + usuario, "Ok");
	}

	static void verificarCriacaoUsuarioOk(NovoAuditor auditor, Integer transacao,
			String usuarioLogado, String usuarioCriado) {
		verify(auditor).transacao(transacao, usuarioLogado,
				"Criação do usuário " + usuarioCriado, "Ok");
	}

	static void verificarCriacaoTitularOk(NovoAuditor auditor, Integer transacao,
			String usuarioLogado, String titularCriado) {
		verify(auditor).transacao(transacao, usuarioLogado,
				"Criação do titular " + titularCriado, "Ok");
	}

	static void verificarCriacaoFontePagadoraOk(NovoAuditor auditor, Integer transacao,
			String usuarioLogado, String fontePagadoraCriada) {
		verify(auditor).transacao(transacao, usuarioLogado,
				"Criação da fonte pagadora " + fontePagadoraCriada, "Ok");
	}

	static void verificarCriacaoDependenteOk(NovoAuditor auditor, Integer transacao,
			String usuarioLogado, String dependenteCriado) {
		verify(auditor).transacao(transacao, usuarioLogado,
				"Criação do dependente " + dependenteCriado, "Ok");
	}

	static void verificarCriacaoGastoOk(NovoAuditor auditor, Integer transacao,
			String usuarioLogado, String gastoCriado) {
		verify(auditor).transacao(transacao, usuarioLogado,
				"Criação do gasto " + gastoCriado, "Ok");
	}
}
