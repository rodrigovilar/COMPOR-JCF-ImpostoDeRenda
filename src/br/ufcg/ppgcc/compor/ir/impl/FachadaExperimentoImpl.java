package br.ufcg.ppgcc.compor.ir.impl;

import java.util.List;

import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class FachadaExperimentoImpl implements FachadaExperimento {
	
	private LogicaTitular logicaTitular = LogicaTitular.getInstance();
	private LogicaFontePagadora logicaFontePagadora = new LogicaFontePagadora();

	public FachadaExperimentoImpl() {
		logicaTitular.limpar();
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
}
