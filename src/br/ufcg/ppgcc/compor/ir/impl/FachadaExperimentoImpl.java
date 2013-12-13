package br.ufcg.ppgcc.compor.ir.impl;

import java.util.List;

import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class FachadaExperimentoImpl implements FachadaExperimento {

	private LogicaTitular logicaTitular = LogicaTitular.getInstance();
	private LogicaFontePagadora logicaFontePagadora = LogicaFontePagadora
			.getInstance();
	private LogicaDependente logicaDependente = LogicaDependente.getInstancia();
	private LogicaDeclaracaoCompleta logicaDeclaracaoCompleta = new LogicaDeclaracaoCompleta();

	public FachadaExperimentoImpl() {
		logicaTitular.limpar();
		logicaFontePagadora.limpar();
	}

	public void criarNovoTitular(Titular titular) {
		logicaTitular.criarNovoTitular(titular);
	}

	public List<Titular> listarTitulares() {
		return logicaTitular.listarTitulares();
	}

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		logicaFontePagadora.criarFontePagadora(titular, fonte);
	}

	public List<FontePagadora> listarFontes(Titular titular) {
		return logicaFontePagadora.getFontes(titular);
	}

	@Override
	public void criarDependente(Titular titular, Dependente dependente) {
		logicaDependente.criarDependente(titular, dependente);
	}

	@Override
	public List<Dependente> listarDependentes(Titular titular) {
		return logicaDependente.getDependentes(titular);
	}

	@Override
	public Resultado declaracaoCompleta(Titular titular) {
		return logicaDeclaracaoCompleta.declaracaoCompleta(titular);
	}
}
