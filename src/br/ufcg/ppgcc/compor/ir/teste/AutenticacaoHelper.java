package br.ufcg.ppgcc.compor.ir.teste;

import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;

public class AutenticacaoHelper {

	static void adminCriarUsuarioLogar(FachadaExperimento fachada,
			String login, String senha) {
		fachada.login("admin", "admin");
		fachada.criarUsuario(login, senha);
		fachada.login(login, senha);
	}
}
