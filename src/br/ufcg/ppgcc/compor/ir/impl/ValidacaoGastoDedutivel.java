package br.ufcg.ppgcc.compor.ir.impl;

import net.compor.frameworks.jcf.api.Decorator;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel;
import br.ufcg.ppgcc.compor.ir.fachada.Pessoa;

public class ValidacaoGastoDedutivel extends Decorator<GerenteGastoDedutivel> {

	public ValidacaoGastoDedutivel(GerenteGastoDedutivel innerComponent) {
		super(innerComponent);
	}

	@Service(requiredServices="validarPessoa,validarTitular,verificarLogin")
	public void criarGastoDedutivel(Pessoa pessoa, GastoDedutivel gasto) {
		requestService("verificarLogin");
		Validacao.obrigatorio(gasto.getCnpjCpfReceptor(), "O campo CPF/CNPJ do receptor é obrigatório");
		Validacao.obrigatorio(gasto.getTipo(), "O campo tipo é obrigatório");
		Validacao.numeroDiferenteZero(gasto.getValor(),
				"O campo valor é obrigatório");
		Validacao.numeroMaiorQueZero(gasto.getValor(),
				"O campo valor deve ser maior que zero");
		requestService("validarPessoa", pessoa);

		getInnerComponent().criarGastoDedutivel(pessoa, gasto);
	}

}
