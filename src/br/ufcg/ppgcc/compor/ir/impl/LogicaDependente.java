package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class LogicaDependente {

	private Map<Titular, List<Dependente>> dependentes = new HashMap<Titular, List<Dependente>>();

	public void criarDependente(Titular titular, Dependente dependente) {
		inicializaLista(titular);
		dependentes.get(titular).add(dependente);
	}

	private void inicializaLista(Titular titular) {
		if (dependentes.get(titular) == null) {
			dependentes.put(titular, new ArrayList<Dependente>());
		}
	}

	public List<Dependente> getDependentes(Titular titular) {
		inicializaLista(titular);
		return dependentes.get(titular);
	}

}
