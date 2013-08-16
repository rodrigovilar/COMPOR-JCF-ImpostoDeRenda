package br.ufcg.ppgcc.compor.ir.teste;

import org.junit.Before;
import org.junit.Test;

import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;
import br.ufcg.ppgcc.compor.ir.impl.FachadaExperimentoImpl;


public class TitularTest {

	private FachadaExperimento fachada;

	@Before
	public void iniciar() {
		fachada = new FachadaExperimentoImpl();
	}

	@Test
	public void T_01_01_novoTitular() {
		Titular titular = TitularHelper.criarTitularMinimo();
		TitularHelper.verificaCriacaoTitular(fachada, titular);
	}
	
	@Test
	public void T_01_02_titularCompleto() {
		Titular titular = TitularHelper.criarTitularPadrao();
		TitularHelper.verificaCriacaoTitular(fachada, titular);
	}


}