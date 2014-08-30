package br.ufcg.ppgcc.compor.ir;

import static org.junit.Assert.assertEquals;
import static br.ufcg.ppgcc.compor.ir.FontePagadoraHelper.criarFontePagadoraPorRenda;

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
		cadastrarCalcularImpostoDevido(0, titular1, 15000);

		Titular titular2 = TitularHelper.criarTitularMinimo();
		cadastrarCalcularImpostoDevido(0, titular2, 19000);

		Titular titular3 = TitularHelper.criarTitularPadrao();
		cadastrarCalcularImpostoDevido(0, titular3, 5000, 10000);
	}

	private void cadastrarCalcularImpostoDevido(double impostoDevido,
			Titular titular, int... rendas) {

		fachada.criarNovoTitular(titular);

		for (int renda : rendas) {
			FontePagadora fonte = criarFontePagadoraPorRenda(renda);
			fachada.criarFontePagadora(titular, fonte);
		}

		Resultado completo = fachada.declaracaoCompleta(titular);
		assertEquals(impostoDevido, completo.getImpostoDevido(), 0.1);
	}

	@Test
	public void T_04_02_calculoImpostoFaixa2() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		cadastrarCalcularImpostoDevido(26.6, titular1, 20000);

		Titular titular2 = TitularHelper.criarTitularMinimo();
		cadastrarCalcularImpostoDevido(401.6, titular2, 25000);

		Titular titular3 = TitularHelper.criarTitularMinimo();
		cadastrarCalcularImpostoDevido(701.6, titular3, 15000, 14000);
	}

	@Test
	public void T_04_03_calculoImpostoFaixa3() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		cadastrarCalcularImpostoDevido(818.45, titular1, 30000);

		Titular titular2 = TitularHelper.criarTitularMinimo();
		cadastrarCalcularImpostoDevido(1568.45, titular2, 35000);

		Titular titular3 = TitularHelper.criarTitularMinimo();
		cadastrarCalcularImpostoDevido(2168.45, titular3, 15000, 24000);
	}

	@Test
	public void T_04_04_calculoImpostoFaixa4() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		cadastrarCalcularImpostoDevido(2374.21, titular1, 40000);

		Titular titular2 = TitularHelper.criarTitularMinimo();
		cadastrarCalcularImpostoDevido(3499.21, titular2, 45000);

		Titular titular3 = TitularHelper.criarTitularMinimo();
		cadastrarCalcularImpostoDevido(4399.21, titular3, 15000, 34000);
	}

	@Test
	public void T_04_05_calculoImpostoFaixa5() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		cadastrarCalcularImpostoDevido(4946.62, titular1, 51000);

		Titular titular2 = TitularHelper.criarTitularMinimo();
		cadastrarCalcularImpostoDevido(18421.62, titular2, 100000);

		Titular titular3 = TitularHelper.criarTitularMinimo();
		cadastrarCalcularImpostoDevido(265921.62, titular3, 300000, 500000,
				200000);
	}
}
