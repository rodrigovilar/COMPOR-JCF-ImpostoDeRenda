package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.List;

import br.ufcg.ppgcc.compor.ir.fachada.Titular;


public class LogicaTitular {
	
	private List<Titular> titulares = new ArrayList<Titular>();

	public void criarNovoTitular(Titular titular) {
		titulares.add(titular);
	}

	public List<Titular> listarTitulares() {
		return titulares;
	}

}
