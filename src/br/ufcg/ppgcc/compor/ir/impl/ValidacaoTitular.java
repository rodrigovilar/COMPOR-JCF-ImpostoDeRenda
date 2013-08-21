package br.ufcg.ppgcc.compor.ir.impl;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class ValidacaoTitular {

	static void validar(Titular titular) {
		if(!LogicaTitular.getInstance().listarTitulares().contains(titular)) {
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
		}
	}

}
