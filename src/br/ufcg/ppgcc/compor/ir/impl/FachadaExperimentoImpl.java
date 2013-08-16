package br.ufcg.ppgcc.compor.ir.impl;

import java.util.List;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class FachadaExperimentoImpl extends ComporFacade implements
		FachadaExperimento {

	@Override
	protected void addComponents() {
		add(new GerenteTitular());
	}

	public void criarNovoTitular(Titular titular) {
		requestService("criarTitular", titular);
	}

	@SuppressWarnings("unchecked")
	public List<Titular> listarTitulares() {
		return (List<Titular>) requestService("listarTitulares");
	}
}
