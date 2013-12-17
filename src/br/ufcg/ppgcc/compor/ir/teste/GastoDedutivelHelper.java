package br.ufcg.ppgcc.compor.ir.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel.TipoGasto;
import br.ufcg.ppgcc.compor.ir.fachada.Pessoa;

public class GastoDedutivelHelper {

	static GastoDedutivel criarGastoEducacao1() {
		return criarGastoDedutivel("000.000.000-00", TipoGasto.Educacao, 2000);
	}

	static GastoDedutivel criarGastoEducacao2() {
		return criarGastoDedutivel("111.111.111-11", TipoGasto.Educacao, 3000);
	}

	static GastoDedutivel criarGastoSaude1() {
		return criarGastoDedutivel("222.222.222-22", TipoGasto.Saude, 2000);
	}

	static GastoDedutivel criarGastoSaude2() {
		return criarGastoDedutivel("333.333.333-33", TipoGasto.Saude, 3000);
	}

	static GastoDedutivel criarGastoDedutivel(String cnpjCpfReceptor, TipoGasto tipo,
			double valor) {
		GastoDedutivel gasto = new GastoDedutivel();
		gasto.setCnpjCpfReceptor(cnpjCpfReceptor);
		gasto.setTipo(tipo);
		gasto.setValor(valor);
		return gasto;
	}
	
	static void verificaCriacaoGastosDedutiveis(FachadaExperimento fachada, Pessoa pessoa, GastoDedutivel... gastos) {
		for (GastoDedutivel gasto : gastos) {
			fachada.criarGastoDedutivel(pessoa, gasto);
		}
	
		List<GastoDedutivel> gastosSalvos = fachada.listarGastosDedutiveis(pessoa);
		assertEquals(gastos.length, gastosSalvos.size());
		
		for (int i = 0; i < gastos.length; i++) {
			assertEquals(gastos[i], gastosSalvos.get(i));
		}
		
	}

	static void excecaoCriarGastoDedutivel(FachadaExperimento fachada, Pessoa pessoa, GastoDedutivel dependente, String mensagem) {
		try {
			fachada.criarGastoDedutivel(pessoa, dependente);
			Assert.fail("A criação de dependente deveria ter lançado exceção");
		} catch (ExcecaoImpostoDeRenda e) {
			Assert.assertEquals(e.getMessage(), mensagem);
		} catch (RuntimeException e) {
			Assert.assertEquals(e.getCause().getMessage(), mensagem);
		}
	}

}
