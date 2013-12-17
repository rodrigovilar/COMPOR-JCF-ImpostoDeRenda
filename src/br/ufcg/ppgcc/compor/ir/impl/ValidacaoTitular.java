package br.ufcg.ppgcc.compor.ir.impl;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.Pessoa;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class ValidacaoTitular {

	static void validar(Titular titular) {
		if(!LogicaTitular.getInstance().listarTitulares().contains(titular)) {
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
		}
	}
	
	static void validarPessoa(Pessoa pessoa) {
		if(!LogicaTitular.getInstance().listarTitulares().contains(pessoa) &&
				!LogicaDependente.getInstancia().getDependentes().contains(pessoa)) {
			throw new ExcecaoImpostoDeRenda("Pessoa não cadastrada");
		}
	}

}
