package br.ufcg.ppgcc.compor.ir;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class DependenteTest {

	private FachadaExperimento fachada;

	@Before
	public void iniciar() {
		fachada = FachadaHelper.criarFachada();
	}

	@Test
	public void T_03_01_novoDependente() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);

		Dependente dependente = DependenteHelper.criarDependentePadrao1();
		DependenteHelper.verificaCriacaoDependentes(fachada, titular, dependente);
	}

	@Test
	public void T_03_02_doisDependentesEmUmTitular() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);
		Dependente dependente1 = DependenteHelper.criarDependentePadrao1();
		Dependente dependente2 = DependenteHelper.criarDependentePadrao2();
		DependenteHelper.verificaCriacaoDependentes(fachada, titular, dependente1, dependente2);
	}

	@Test
	public void T_03_03_doisDependentesUmEmCadaTitular() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular1);
		Dependente dependente1 = DependenteHelper.criarDependentePadrao1();

		Titular titular2 = TitularHelper.criarTitularMinimo();
		fachada.criarNovoTitular(titular2);
		Dependente dependente2 = DependenteHelper.criarDependentePadrao2();

		DependenteHelper.verificaCriacaoDependentes(fachada, titular1, dependente1);
		DependenteHelper.verificaCriacaoDependentes(fachada, titular2, dependente2);
	}

	@Test
	public void T_03_04_validacaoDependente() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);

		Dependente dependenteSemCpf = DependenteHelper.criarDependente(null,
				Calendar.getInstance(), "Filho 1", 21);
		DependenteHelper.excecaoCriarDependente(fachada, titular,
				dependenteSemCpf, "O campo CPF é obrigatório");

		Dependente dependenteSemNome = DependenteHelper.criarDependente(
				"000.000.000-00", Calendar.getInstance(), null, 21);
		DependenteHelper.excecaoCriarDependente(fachada, titular,
				dependenteSemNome, "O campo nome é obrigatório");

		Dependente dependenteSemTipo = DependenteHelper.criarDependente(
				"000.000.000-00", Calendar.getInstance(), "Filho", 0);
		DependenteHelper.excecaoCriarDependente(fachada, titular,
				dependenteSemTipo, "O campo tipo é obrigatório");

		Dependente dependenteComTipoInvalido = DependenteHelper
				.criarDependente("000.000.000-00", Calendar.getInstance(),
						"Filho", -10);
		DependenteHelper.excecaoCriarDependente(fachada, titular,
				dependenteComTipoInvalido, "O campo tipo é inválido");
	}
	
	@Test
	public void T_03_05_novoDependenteComCpfInvalido() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);

		Dependente dependente = 
				DependenteHelper.criarDependente("000000000000", Calendar.getInstance(), "Filho 1", 21);
		DependenteHelper.excecaoCriarDependente(fachada, titular,
				dependente, "O campo CPF é inválido");
		
		dependente = 
				DependenteHelper.criarDependente("abc", Calendar.getInstance(), "Filho 1", 21);
		DependenteHelper.excecaoCriarDependente(fachada, titular,
				dependente, "O campo CPF é inválido");

		dependente = 
				DependenteHelper.criarDependente("000.000.000.00a", Calendar.getInstance(), "Filho 1", 21);
		DependenteHelper.excecaoCriarDependente(fachada, titular,
				dependente, "O campo CPF é inválido");
	}

	@Test
	public void T_03_06_novoDependenteComTitularDesconhecido() {
		Titular titular = TitularHelper.criarTitularPadrao();
		Dependente dependente = DependenteHelper.criarDependentePadrao1();
		DependenteHelper.excecaoCriarDependente(fachada, titular, dependente,
				"Titular não cadastrado");
	}
}
