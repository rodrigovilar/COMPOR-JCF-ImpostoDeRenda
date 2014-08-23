package br.ufcg.ppgcc.compor.ir;

import org.junit.Before;
import org.junit.Test;


public class FontePagadoraTest {

	private FachadaExperimento fachada;

	@Before
	public void iniciar() {
		fachada = FachadaHelper.criarFachada();
	}

	@Test
	public void T_02_01_novaFontePagadora() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);
		FontePagadora fonte = FontePagadoraHelper.criarFontePagadoraPadrao1();
		FontePagadoraHelper.verificaCriacaoFontes(fachada, titular, fonte);
	}

	@Test
	public void T_02_02_duasFontesPagadorasEmUmTitular() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPadrao1();
		FontePagadora fonte2 = FontePagadoraHelper.criarFontePagadoraPadrao2();
		FontePagadoraHelper.verificaCriacaoFontes(fachada, titular, fonte1, fonte2);
	}
	
	@Test
	public void T_02_03_duasFontesPagadorasUmaEmCadaTitular() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular1);
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPadrao1();
		FontePagadoraHelper.verificaCriacaoFontes(fachada, titular1, fonte1);

		Titular titular2 = TitularHelper.criarTitularMinimo();
		fachada.criarNovoTitular(titular2);
		FontePagadora fonte2 = FontePagadoraHelper.criarFontePagadoraPadrao2();
		FontePagadoraHelper.verificaCriacaoFontes(fachada, titular2, fonte2);
	}

}
