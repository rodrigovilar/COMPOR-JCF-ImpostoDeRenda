package br.ufcg.ppgcc.compor.ir;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class FontePagadoraHelper {

	static FontePagadora criarFontePagadora(String nome, String cpfCnpj,
			double rendimentoRecebidos) {
		FontePagadora fonte = new FontePagadora();
		fonte.setNome(nome);
		fonte.setCpfCnpj(cpfCnpj);
		fonte.setRendimentoRecebidos(rendimentoRecebidos);
		return fonte;
	}

	static FontePagadora criarFontePagadoraPadrao1() {
		return criarFontePagadora("UFCG", "00.000.000/0000-00", 50000);
	}

	static FontePagadora criarFontePagadoraPadrao2() {
		return criarFontePagadora("UFPB", "00.000.000/0000-00", 20000);
	}

	static void verificaCriacaoFontes(FachadaExperimento fachada, Titular titular, FontePagadora... fontes) {
		for (FontePagadora fonte : fontes) {
			fachada.criarFontePagadora(titular, fonte);
		}
	
		List<FontePagadora> fontesSalvas = fachada.listarFontes(titular);
		assertEquals(fontes.length, fontesSalvas.size());
		
		for (int i = 0; i < fontes.length; i++) {
			assertEquals(fontes[i], fontesSalvas.get(i));
		}
		
	}

}
