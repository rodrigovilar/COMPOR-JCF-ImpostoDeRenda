package br.ufcg.ppgcc.compor.ir.teste;

import org.junit.Before;
import org.junit.Test;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.impl.FachadaExperimentoImpl;

public class AutenticacaoTest {

	private FachadaExperimento fachada;

	@Before
	public void iniciar() {
		// Coloque sua Fachada aqui, com segurança habilitada.
		fachada = new FachadaExperimentoImpl();
	}

	@Test
	public void T_06_01_loginDefaultCorreto() {
		fachada.login("admin", "admin");
	}

	@Test(expected=ExcecaoImpostoDeRenda.class)
	public void T_06_02_loginDefaultSenhaErrada() {
		fachada.login("admin", "admin2");
	}

	@Test(expected=ExcecaoImpostoDeRenda.class)
	public void T_06_03_loginDefaultDesconhecido() {
		fachada.login("admin2", "admin");
	}
	
	@Test
	public void T_06_04_criarUsuarioLogar() {
		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
	}

	@Test(expected=ExcecaoImpostoDeRenda.class)
	public void T_06_05_criarUsuarioSemPermissao() {
		fachada.criarUsuario("usuario", "senha");
	}

}
