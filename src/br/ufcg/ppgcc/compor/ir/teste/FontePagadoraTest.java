package br.ufcg.ppgcc.compor.ir.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;
import br.ufcg.ppgcc.compor.ir.impl.FachadaExperimentoImpl;

public class FontePagadoraTest {

	private FachadaExperimento fachada;

	@Before
	public void iniciar() {
		fachada = new FachadaExperimentoImpl();
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
