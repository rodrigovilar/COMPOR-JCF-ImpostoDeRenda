package br.ufcg.ppgcc.compor.ir.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class FontePagadoraHelper {

	static FontePagadora criarFontePagadoraPorRenda(int renda) {
		return criarFontePagadora("UFCG", "00.000.000/0000-00", renda);
	}

	static FontePagadora criarFontePagadora(String nome, String cpfCnpj,
			double rendimentoRecebidos) {
		return criarFontePagadora(nome, cpfCnpj, rendimentoRecebidos, 0);
	}

	static FontePagadora criarFontePagadoraPorRendaEImpostoPago(int renda, double impostoPago) {
		return criarFontePagadora("UFCG", "00.000.000/0000-00", renda, impostoPago);
	}
	
	static FontePagadora criarFontePagadora(String nome, String cpfCnpj,
			double rendimentoRecebidos, double impostoPago) {
		FontePagadora fonte = new FontePagadora();
		fonte.setNome(nome);
		fonte.setCpfCnpj(cpfCnpj);
		fonte.setImpostoPago(impostoPago);
		fonte.setRendimentosRecebidos(rendimentoRecebidos);
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

	static void excecaoCriarFonte(FachadaExperimento fachada, Titular titular, FontePagadora fonte, String mensagem) {
		try {
			fachada.criarFontePagadora(titular, fonte);
			Assert.fail("A criação de Fonte pagadora deveria ter lançado exceção");
		} catch (ExcecaoImpostoDeRenda e) {
			Assert.assertEquals(e.getMessage(), mensagem);
		} catch (RuntimeException e) {
			Assert.assertEquals(e.getCause().getMessage(), mensagem);
		}
	}

}
