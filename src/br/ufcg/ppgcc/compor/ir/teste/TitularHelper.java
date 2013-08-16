package br.ufcg.ppgcc.compor.ir.teste;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import br.ufcg.ppgcc.compor.ir.fachada.Endereco;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class TitularHelper {

	static Titular criarTitularMinimo() {
		Titular titular = new Titular();
		titular.setNome("Jose");
		titular.setCpf("000.000.000-00");
		return titular;
	}
	
	static Titular criarTitularPadrao() {
		Titular titular = criarTitularMinimo();
		titular.setDataNascimento(Calendar.getInstance());
		titular.setTituloEleitoral("000");
		titular.setEndereco(criarEnderecoPadrao());
		titular.setNaturezaOcupacao(10);
		titular.setOcupacaoPrincipal(100);
		return titular;
	}

	static Endereco criarEnderecoPadrao() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Log");
		endereco.setNumero(0);
		endereco.setComplemento("A");
		endereco.setBairro("Bai");
		endereco.setMunicipio("Mun");
		endereco.setEstado("Est");
		endereco.setCEP("00000-000");
		return endereco;
	}

	static void verificaCriacaoTitulares(FachadaExperimento fachada, Titular... titulares) {
		for (Titular titular : titulares) {
			fachada.criarNovoTitular(titular);
		}
	
		List<Titular> titularesSalvos = fachada.listarTitulares();
		assertEquals(titulares.length, titularesSalvos.size());
		
		for (int i = 0; i < titulares.length; i++) {
			assertEquals(titulares[i], titularesSalvos.get(i));
		}
		
	}

}
