package br.ufcg.ppgcc.compor.ir.impl;

import java.util.List;

import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class FachadaExperimentoImpl implements FachadaExperimento {
	
	private LogicaTitular logicaTitular = new LogicaTitular();
	

	public void criarNovoTitular(Titular titular) {
		logicaTitular.criarNovoTitular(titular);
	}

	public List<Titular> listarTitulares() {
		return logicaTitular.listarTitulares();
	}


}
