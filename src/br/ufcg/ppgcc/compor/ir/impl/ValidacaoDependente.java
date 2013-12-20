package br.ufcg.ppgcc.compor.ir.impl;

import net.compor.frameworks.jcf.api.Decorator;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class ValidacaoDependente extends Decorator<GerenteDependente> {

	public ValidacaoDependente(GerenteDependente innerComponent) {
		super(innerComponent);
	}

	@Service(requiredServices="iniciarTransacao,validarTitular,verificarLogin,concluirTransacao")
	public void criarDependente(Titular titular, Dependente dependente) {
		requestService("iniciarTransacao", "Criação do dependente " + dependente.getNome());
		requestService("verificarLogin");
		Validacao.obrigatorio(dependente.getNome(), "O campo nome é obrigatório");
		Validacao.obrigatorio(dependente.getCpf(), "O campo CPF é obrigatório");
		Validacao.numeroDiferenteZero(dependente.getTipo(), "O campo tipo é obrigatório");
		Validacao.numeroMaiorQueZero(dependente.getTipo(), "O campo tipo é inválido");
		Validacao.cpf(dependente.getCpf(), "O campo CPF é inválido");
		requestService("validarTitular", titular);

		getInnerComponent().criarDependente(titular, dependente);

		requestService("concluirTransacao");
	}

}
