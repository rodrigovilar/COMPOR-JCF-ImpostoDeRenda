package br.ufcg.ppgcc.compor.ir.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class ImpostoAPagarTest {

	private FachadaExperimento fachada;

	@Before
	public void iniciar() {
		// Coloque sua Fachada aqui.
		fachada = null;
	}

	@Test
	public void T_05_01_calculoImpostoIsento() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(15000, 1000);
		cadastrarCalcularImpostoDevido(0, 1000, -1000, titular1, fonte1);

		Titular titular2 = TitularHelper.criarTitularMinimo();
		FontePagadora fonte2 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(15000, 1000);
		FontePagadora fonte3 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(4000, 1000);
		cadastrarCalcularImpostoDevido(0, 2000, -2000, titular2, fonte2, fonte3);
	}

	@Test
	public void T_05_02_calculoImpostoFaixa2() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(20000, 1000);
		cadastrarCalcularImpostoDevido(26.6, 1000, -973.4, titular1, fonte1);

		Titular titular2 = TitularHelper.criarTitularMinimo();
		FontePagadora fonte2 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(20000, 1000);
		FontePagadora fonte3 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(5000, 500);
		cadastrarCalcularImpostoDevido(401.6, 1500, -1098.4, titular2, fonte2, fonte3);

		Titular titular3 = TitularHelper.criarTitularPadrao2();
		FontePagadora fonte4 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(20000, 400);
		FontePagadora fonte5 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(5000, 0);
		FontePagadora fonte6 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(4000, 0);
		cadastrarCalcularImpostoDevido(701.6, 400, 301.6, titular3, fonte4, fonte5, fonte6);
	}

	@Test
	public void T_05_03_calculoImpostoFaixa3() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(30000, 100);
		cadastrarCalcularImpostoDevido(818.45, 100, 718.45, titular1, fonte1);

		Titular titular2 = TitularHelper.criarTitularMinimo();
		FontePagadora fonte2 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(20000, 1200);
		FontePagadora fonte3 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(15000, 500);
		cadastrarCalcularImpostoDevido(1568.45, 1700, -131.55, titular2, fonte2, fonte3);
	}
	
	@Test
	public void T_05_04_calculoImpostoFaixa4() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		FontePagadora fonte1 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(40000, 2374.21);
		cadastrarCalcularImpostoDevido(2374.21, 2374.21, 0, titular1, fonte1);
	}

	@Test
	public void T_05_05_calculoImpostoFaixa5() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		FontePagadora fonte2 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(20000, 2000);
		FontePagadora fonte3 = FontePagadoraHelper.criarFontePagadoraPorRendaEImpostoPago(31000, 2946.62);
		cadastrarCalcularImpostoDevido(4946.62, 4946.62, 0, titular1, fonte2, fonte3);
	}
	
	private void cadastrarCalcularImpostoDevido(double impostoDevido, double impostoPago, 
			double impostoAPagar, Titular titular, FontePagadora... fontes) {

		fachada.criarNovoTitular(titular);

		for (FontePagadora fonte : fontes) {
			fachada.criarFontePagadora(titular, fonte);
		}

		Resultado completo = fachada.declaracaoCompleta(titular);
		assertEquals(impostoDevido, completo.getImpostoDevido(), 0.1);
		assertEquals(impostoAPagar, completo.getImpostoAPagar(), 0.1);
		assertEquals(impostoPago, completo.getImpostoPago(), 0.1);
	}
}
