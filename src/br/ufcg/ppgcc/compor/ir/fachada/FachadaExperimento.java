package br.ufcg.ppgcc.compor.ir.fachada;

import java.util.List;

public interface FachadaExperimento {

	void criarNovoTitular(Titular titular);

	List<Titular> listarTitulares();


}