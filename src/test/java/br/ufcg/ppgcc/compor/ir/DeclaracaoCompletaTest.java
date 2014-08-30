package br.ufcg.ppgcc.compor.ir;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DeclaracaoCompletaTest {

	private FachadaExperimento fachada;

	@Before
	public void iniciar() {
		fachada = FachadaHelper.criarFachada();
	}


	@Test
	public void T_04_01_calculoImpostoIsento() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular1);
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPorRenda(15000);
		fachada.criarFontePagadora(titular1, fonte1);		
		Resultado completo = fachada.declaracaoCompleta(titular1);
		assertEquals(0, completo.getImpostoDevido(), 0.1);

		Titular titular2 = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular2);
		FontePagadora fonte2 = FontePagadoraHelper.criarFontePagadoraPorRenda(19000);
		fachada.criarFontePagadora(titular2, fonte2);
		completo = fachada.declaracaoCompleta(titular2);
		assertEquals(0, completo.getImpostoDevido(), 0.1);

		Titular titular3 = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular2);
		FontePagadora fonte31 = FontePagadoraHelper.criarFontePagadoraPorRenda(5000);
		fachada.criarFontePagadora(titular3, fonte31);
		FontePagadora fonte32 = FontePagadoraHelper.criarFontePagadoraPorRenda(10000);
		fachada.criarFontePagadora(titular3, fonte32);
		completo = fachada.declaracaoCompleta(titular3);
		assertEquals(0, completo.getImpostoDevido(), 0.1);
	}
}
