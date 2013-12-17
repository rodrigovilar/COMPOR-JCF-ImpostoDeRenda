package br.ufcg.ppgcc.compor.ir.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel.TipoGasto;
import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;
import br.ufcg.ppgcc.compor.ir.impl.FachadaExperimentoImpl;

public class GastoDedutivelTest {

	private FachadaExperimento fachada;

	@Before
	public void iniciar() {
		// Coloque sua Fachada aqui.
		fachada = new FachadaExperimentoImpl(); 
	}

	@Test
	public void T_07_01_novoGastoDedutivelTitular() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);

		GastoDedutivel gasto = GastoDedutivelHelper.criarGastoEducacao1();
		GastoDedutivelHelper.verificaCriacaoGastosDedutiveis(fachada, titular, gasto);
	}
	
	@Test
	public void T_07_02_novoGastoDedutivelDependente() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);
		Dependente dependente = DependenteHelper.criarDependentePadrao1();
		fachada.criarDependente(titular, dependente);

		GastoDedutivel gasto = GastoDedutivelHelper.criarGastoSaude1();
		GastoDedutivelHelper.verificaCriacaoGastosDedutiveis(fachada, dependente, gasto);
	}

	@Test
	public void T_07_03_doisGastosEmUmTitular() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);

		GastoDedutivel gasto1 = GastoDedutivelHelper.criarGastoEducacao1();
		GastoDedutivel gasto2 = GastoDedutivelHelper.criarGastoEducacao2();
		GastoDedutivelHelper.verificaCriacaoGastosDedutiveis(fachada, titular, gasto1, gasto2);
	}

	@Test
	public void T_07_04_doisGastosUmEmCadaTitular() {
		Titular titular1 = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular1);
		GastoDedutivel gasto1 = GastoDedutivelHelper.criarGastoEducacao1();

		Titular titular2 = TitularHelper.criarTitularMinimo();
		fachada.criarNovoTitular(titular2);
		GastoDedutivel gasto2 = GastoDedutivelHelper.criarGastoSaude1();

		GastoDedutivelHelper.verificaCriacaoGastosDedutiveis(fachada, titular1, gasto1);
		GastoDedutivelHelper.verificaCriacaoGastosDedutiveis(fachada, titular2, gasto2);
	}

	@Test
	public void T_07_05_novosGastosDedutiveisTitularDependente() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);
		GastoDedutivel gasto1 = GastoDedutivelHelper.criarGastoEducacao1();

		Dependente dependente = DependenteHelper.criarDependentePadrao1();
		fachada.criarDependente(titular, dependente);
		GastoDedutivel gasto2 = GastoDedutivelHelper.criarGastoSaude1();

		GastoDedutivelHelper.verificaCriacaoGastosDedutiveis(fachada, titular, gasto1);
		GastoDedutivelHelper.verificaCriacaoGastosDedutiveis(fachada, dependente, gasto2);
	}

	@Test
	public void T_07_06_validacaoGastoDedutivel() {
		Titular titular = TitularHelper.criarTitularPadrao();
		fachada.criarNovoTitular(titular);
		
		GastoDedutivel gastoSemCpfCnpj = 
				GastoDedutivelHelper.criarGastoDedutivel(null, TipoGasto.Educacao, 100);
		GastoDedutivelHelper.excecaoCriarGastoDedutivel(fachada, titular, gastoSemCpfCnpj, 
				"O campo CPF/CNPJ do receptor é obrigatório");

		GastoDedutivel gastoSemTipo = 
				GastoDedutivelHelper.criarGastoDedutivel("000.000.000-00", null, 100);
		GastoDedutivelHelper.excecaoCriarGastoDedutivel(fachada, titular, gastoSemTipo, 
				"O campo tipo é obrigatório");

		GastoDedutivel gastoSemValor = 
				GastoDedutivelHelper.criarGastoDedutivel("000.000.000-00", TipoGasto.Educacao, 0);
		GastoDedutivelHelper.excecaoCriarGastoDedutivel(fachada, titular, gastoSemValor, 
				"O campo valor é obrigatório");

		GastoDedutivel gastoComValorInvalido = 
				GastoDedutivelHelper.criarGastoDedutivel("000.000.000-00", TipoGasto.Educacao, -120);
		GastoDedutivelHelper.excecaoCriarGastoDedutivel(fachada, titular, gastoComValorInvalido, 
				"O campo valor deve ser maior que zero");
	}
	
	@Test
	public void T_07_07_novoGastoComPessoaDesconhecida() {
		Titular titular = TitularHelper.criarTitularPadrao();
		GastoDedutivel gasto1 = GastoDedutivelHelper.criarGastoEducacao1();
		GastoDedutivelHelper.excecaoCriarGastoDedutivel(fachada, titular, gasto1, 
				"Pessoa não cadastrada");

		Dependente dependente = DependenteHelper.criarDependentePadrao1();
		GastoDedutivel gasto2 = GastoDedutivelHelper.criarGastoEducacao2();
		GastoDedutivelHelper.excecaoCriarGastoDedutivel(fachada, dependente, gasto2, 
				"Pessoa não cadastrada");
	}
	
	@Test
	public void T_07_08_calculoImpostoFaixa4DeducaoEducacaoTitular() {
		Titular titular = TitularHelper.criarTitularPadrao();
		FontePagadora fonte = FontePagadoraHelper.criarFontePagadoraPorRenda(100000);
		fachada.criarNovoTitular(titular);
		fachada.criarFontePagadora(titular, fonte);
		
		GastoDedutivel gastoDedutivel = 
				GastoDedutivelHelper.criarGastoDedutivel("00.000.000/0000-00", TipoGasto.Educacao, 10000);
		fachada.criarGastoDedutivel(titular, gastoDedutivel);
		
		Resultado completo = fachada.declaracaoCompleta(titular);
		assertEquals(17571.49, completo.getImpostoDevido(), 0.1);
	}

	@Test
	public void T_07_09_calculoImpostoFaixa4DeducaoEducacaoDependente() {
		Titular titular = TitularHelper.criarTitularPadrao();
		FontePagadora fonte = FontePagadoraHelper.criarFontePagadoraPorRenda(100000);
		Dependente dependente = DependenteHelper.criarDependentePadrao1();
		fachada.criarNovoTitular(titular);
		fachada.criarFontePagadora(titular, fonte);
		fachada.criarDependente(titular, dependente);
		
		GastoDedutivel gastoDedutivel = 
				GastoDedutivelHelper.criarGastoDedutivel("00.000.000/0000-00", TipoGasto.Educacao, 10000);
		fachada.criarGastoDedutivel(dependente, gastoDedutivel);

		Resultado completo = fachada.declaracaoCompleta(titular);
		assertEquals(17028.45, completo.getImpostoDevido(), 0.1);
	}

	@Test
	public void T_07_10_calculoImpostoFaixa4DeducaoSaudeTitular() {
		Titular titular = TitularHelper.criarTitularPadrao();
		FontePagadora fonte = FontePagadoraHelper.criarFontePagadoraPorRenda(100000);
		fachada.criarNovoTitular(titular);
		fachada.criarFontePagadora(titular, fonte);
		
		GastoDedutivel gastoDedutivel = 
				GastoDedutivelHelper.criarGastoDedutivel("00.000.000/0000-00", TipoGasto.Saude, 10000);
		fachada.criarGastoDedutivel(titular, gastoDedutivel);
		
		Resultado completo = fachada.declaracaoCompleta(titular);
		assertEquals(15671.62, completo.getImpostoDevido(), 0.1);
	}

	@Test
	public void T_07_11_calculoImpostoFaixa4DeducaoSaudeDependente() {
		Titular titular = TitularHelper.criarTitularPadrao();
		FontePagadora fonte = FontePagadoraHelper.criarFontePagadoraPorRenda(100000);
		Dependente dependente = DependenteHelper.criarDependentePadrao1();
		fachada.criarNovoTitular(titular);
		fachada.criarFontePagadora(titular, fonte);
		fachada.criarDependente(titular, dependente);
		
		GastoDedutivel gastoDedutivel = 
				GastoDedutivelHelper.criarGastoDedutivel("00.000.000/0000-00", TipoGasto.Saude, 10000);
		fachada.criarGastoDedutivel(dependente, gastoDedutivel);

		Resultado completo = fachada.declaracaoCompleta(titular);
		assertEquals(15128.57, completo.getImpostoDevido(), 0.1);
	}

}
