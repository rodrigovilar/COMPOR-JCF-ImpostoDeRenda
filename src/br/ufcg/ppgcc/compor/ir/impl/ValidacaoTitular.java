package br.ufcg.ppgcc.compor.ir.impl;

import net.compor.frameworks.jcf.api.Decorator;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class ValidacaoTitular extends Decorator<GerenteTitular> {

	public ValidacaoTitular(GerenteTitular innerComponent) {
		super(innerComponent);
	}

	@Service
	public void criarTitular(Titular titular) {
		Validacao.obrigatorio(titular.getNome(), "O campo nome é obrigatório");
		Validacao.obrigatorio(titular.getCpf(), "O campo CPF é obrigatório");
		Validacao.cpf(titular.getCpf(), "O campo CPF está inválido");
		getInnerComponent().criarTitular(titular);
	}
	

}
