package br.ufcg.ppgcc.compor.ir.teste;

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

}
