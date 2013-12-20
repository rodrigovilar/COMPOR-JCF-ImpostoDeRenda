package br.ufcg.ppgcc.compor.ir.teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import br.ufcg.ppgcc.compor.ir.fachada.Auditor;
import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel;
import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;
import br.ufcg.ppgcc.compor.ir.impl.FachadaExperimentoImpl;

public class AuditoriaTest {

	private FachadaExperimento fachada;

	@Mock
	private Auditor auditor;
	
	@Before
	public void iniciar() {
		MockitoAnnotations.initMocks(this);

		// Coloque sua Fachada aqui, com segurança habilitada.
		fachada = new FachadaExperimentoImpl(true);
		fachada.setAuditor(auditor);
	}

	@Test
	public void T_08_01_loginDefaultCorreto() {
		fachada.login("admin", "admin");
		AuditoriaHelper.verificarLoginDefaultOk(auditor, 1);
	}

	@Test
	public void T_08_02_loginDefaultSenhaErrada() {
		try {
			fachada.login("admin", "admin2");
		} catch (ExcecaoImpostoDeRenda e) {}

		verify(auditor).iniciandoTransacao(1, null, "Login default");
		verify(auditor).transacaoAbortada(1, "Senha errada");
	}

	@Test
	public void T_08_03_loginDefaultDesconhecido() {
		try {
			fachada.login("admin2", "admin");
		} catch (ExcecaoImpostoDeRenda e) {}

		verify(auditor).iniciandoTransacao(1, null, "Login admin2");
		verify(auditor).transacaoAbortada(1, "Usuário desconhecido");
	}
	
	@Test
	public void T_08_04_criarUsuarioLogar() {
		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
		AuditoriaHelper.verificarLoginDefaultOk(auditor, 1);
		AuditoriaHelper.verificarCriacaoUsuarioOk(auditor, 2, "admin", "usuario");
		AuditoriaHelper.verificarLoginOk(auditor, 3, "usuario");
	}

	@Test
	public void T_08_05_criarUsuarioSemPermissao() {
		try {
			fachada.criarUsuario("usuario", "senha");
		} catch (ExcecaoImpostoDeRenda e) {}
		
		verify(auditor).iniciandoTransacao(1, null,
				"Criação do usuário usuario");
		verify(auditor).transacaoAbortada(1, "Usuário não logado");
	}

	@Test
	public void T_08_06_criarTitular() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		TitularHelper.excecaoCriarTitular(fachada, titular1, "Usuário não logado");
		
		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
		TitularHelper.verificaCriacaoTitulares(fachada, titular1);
		
		verify(auditor).iniciandoTransacao(1, null,
				"Criação do titular Jose");
		verify(auditor).transacaoAbortada(1, "Usuário não logado");
		
		AuditoriaHelper.verificarLoginDefaultOk(auditor, 2);
		AuditoriaHelper.verificarCriacaoUsuarioOk(auditor, 3, "admin", "usuario");
		AuditoriaHelper.verificarLoginOk(auditor, 4, "usuario");

		AuditoriaHelper.verificarCriacaoTitularOk(auditor, 5, "usuario", "Jose");
	}

	@Test
	public void T_08_07_criarFontePagadora() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPadrao1();
		FontePagadoraHelper.excecaoCriarFonte(fachada, titular1, fonte1, "Usuário não logado");
		
		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
		TitularHelper.verificaCriacaoTitulares(fachada, titular1);
		FontePagadoraHelper.verificaCriacaoFontes(fachada, titular1, fonte1);

		verify(auditor).iniciandoTransacao(1, null,
				"Criação da fonte pagadora UFCG");
		verify(auditor).transacaoAbortada(1, "Usuário não logado");

		AuditoriaHelper.verificarLoginDefaultOk(auditor, 2);
		AuditoriaHelper.verificarCriacaoUsuarioOk(auditor, 3, "admin", "usuario");
		AuditoriaHelper.verificarLoginOk(auditor, 4, "usuario");

		AuditoriaHelper.verificarCriacaoTitularOk(auditor, 5, "usuario", "Jose");
		AuditoriaHelper.verificarCriacaoFontePagadoraOk(auditor, 6, "usuario", "UFCG");
	}

	@Test
	public void T_08_08_criarDependente() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		Dependente dependente1 = DependenteHelper.criarDependentePadrao1();
		DependenteHelper.excecaoCriarDependente(fachada, titular1, dependente1, "Usuário não logado");
		
		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
		TitularHelper.verificaCriacaoTitulares(fachada, titular1);
		DependenteHelper.verificaCriacaoDependentes(fachada, titular1, dependente1);

		verify(auditor).iniciandoTransacao(1, null,
				"Criação do dependente Filho 1");
		verify(auditor).transacaoAbortada(1, "Usuário não logado");

		AuditoriaHelper.verificarLoginDefaultOk(auditor, 2);
		AuditoriaHelper.verificarCriacaoUsuarioOk(auditor, 3, "admin", "usuario");
		AuditoriaHelper.verificarLoginOk(auditor, 4, "usuario");

		AuditoriaHelper.verificarCriacaoTitularOk(auditor, 5, "usuario", "Jose");
		AuditoriaHelper.verificarCriacaoDependenteOk(auditor, 6, "usuario", "Filho 1");
	}

	@Test
	public void T_08_09_criarGastoDedutivel() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		GastoDedutivel gasto1 = GastoDedutivelHelper.criarGastoEducacao1();
		GastoDedutivelHelper.excecaoCriarGastoDedutivel(fachada, titular1, gasto1, "Usuário não logado");
		
		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
		TitularHelper.verificaCriacaoTitulares(fachada, titular1);
		GastoDedutivelHelper.verificaCriacaoGastosDedutiveis(fachada, titular1, gasto1);

		verify(auditor).iniciandoTransacao(1, null,
				"Criação do gasto Educacao");
		verify(auditor).transacaoAbortada(1, "Usuário não logado");

		AuditoriaHelper.verificarLoginDefaultOk(auditor, 2);
		AuditoriaHelper.verificarCriacaoUsuarioOk(auditor, 3, "admin", "usuario");
		AuditoriaHelper.verificarLoginOk(auditor, 4, "usuario");

		AuditoriaHelper.verificarCriacaoTitularOk(auditor, 5, "usuario", "Jose");
		AuditoriaHelper.verificarCriacaoGastoOk(auditor, 6, "usuario", "Educacao");
	}

	@Test
	public void T_08_10_declaracaoCompleta() {
		Titular titular1 = TitularHelper.criarTitularPadrao();

		try {
			fachada.declaracaoCompleta(titular1);
			Assert.fail("A declaração completa deveria ter lançado exceção");
		} catch (ExcecaoImpostoDeRenda e) {
			Assert.assertEquals(e.getMessage(), "Usuário não logado");
		} catch (RuntimeException e) {
			Assert.assertEquals(e.getCause().getMessage(), "Usuário não logado");
		}

		AutenticacaoHelper.adminCriarUsuarioLogar(fachada, "usuario", "senha");
		
		TitularHelper.verificaCriacaoTitulares(fachada, titular1);
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPadrao1();
		FontePagadoraHelper.verificaCriacaoFontes(fachada, titular1, fonte1);
		Resultado completa = fachada.declaracaoCompleta(titular1);
		Assert.assertNotNull(completa);
		
		verify(auditor).iniciandoTransacao(1, null,
				"Relatório da Declaração completa para Jose");
		verify(auditor).transacaoAbortada(1, "Usuário não logado");

		AuditoriaHelper.verificarLoginDefaultOk(auditor, 2);
		AuditoriaHelper.verificarCriacaoUsuarioOk(auditor, 3, "admin", "usuario");
		AuditoriaHelper.verificarLoginOk(auditor, 4, "usuario");

		AuditoriaHelper.verificarCriacaoTitularOk(auditor, 5, "usuario", "Jose");
		AuditoriaHelper.verificarCriacaoFontePagadoraOk(auditor, 6, "usuario", "UFCG");

		verify(auditor).iniciandoTransacao(7, "usuario",
				"Relatório da Declaração completa para Jose");
		verify(auditor).transacaoConcluida(7);
	}
}
