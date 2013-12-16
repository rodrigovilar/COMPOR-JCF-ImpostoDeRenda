package br.ufcg.ppgcc.compor.ir.teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.impl.FachadaExperimentoImpl;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class AutenticacaoTest {

	private FachadaExperimento fachada;

	@Before
	public void iniciar() {
		// Coloque sua Fachada aqui, com segurança habilitada.
		fachada = new FachadaExperimentoImpl(true);
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

	@Test
	public void T_06_06_criarTitular() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		TitularHelper.excecaoCriarTitular(fachada, titular1, "Usuário não logado");
		
		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
		TitularHelper.verificaCriacaoTitulares(fachada, titular1);
	}

	@Test
	public void T_06_07_criarFontePagadora() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPadrao1();
		FontePagadoraHelper.excecaoCriarFonte(fachada, titular1, fonte1, "Usuário não logado");
		
		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
		TitularHelper.verificaCriacaoTitulares(fachada, titular1);
		FontePagadoraHelper.verificaCriacaoFontes(fachada, titular1, fonte1);
	}

	@Test
	public void T_06_08_criarDependente() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		Dependente dependente1 = DependenteHelper.criarDependentePadrao1();
		DependenteHelper.excecaoCriarDependente(fachada, titular1, dependente1, "Usuário não logado");
		
		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
		TitularHelper.verificaCriacaoTitulares(fachada, titular1);
		DependenteHelper.verificaCriacaoDependentes(fachada, titular1, dependente1);
	}

	@Test
	public void T_06_09_declaracaoCompleta() {
		Titular titular1 = TitularHelper.criarTitularPadrao();

		try {
			fachada.declaracaoCompleta(titular1);
			Assert.fail("A declaração completa deveria ter lançado exceção");
		} catch (ExcecaoImpostoDeRenda e) {
			Assert.assertEquals(e.getMessage(), "Usuário não logado");
		}

		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
		
		TitularHelper.verificaCriacaoTitulares(fachada, titular1);
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPadrao1();
		FontePagadoraHelper.verificaCriacaoFontes(fachada, titular1, fonte1);
		Resultado completa = fachada.declaracaoCompleta(titular1);
		Assert.assertNotNull(completa);
	}
}
