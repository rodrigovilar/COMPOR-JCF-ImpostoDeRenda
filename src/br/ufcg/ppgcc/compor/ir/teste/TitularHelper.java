package br.ufcg.ppgcc.compor.ir.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class TitularHelper {

	static Titular criarTitularMinimo() {
		Titular titular = new Titular();
		titular.setNome("Jose");
		titular.setCpf("000.000.000-00");
		return titular;
	}

	static void verificaCriacaoTitular(FachadaExperimento fachada, Titular titular) {
		fachada.criarNovoTitular(titular);
		List<Titular> titulares = fachada.listarTitulares();
	
		assertEquals(1, titulares.size());
		assertEquals(titular, titulares.get(0));
	}

}
