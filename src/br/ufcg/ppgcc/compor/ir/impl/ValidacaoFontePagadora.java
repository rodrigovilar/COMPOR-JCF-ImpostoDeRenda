package br.ufcg.ppgcc.compor.ir.impl;

import net.compor.frameworks.jcf.api.Decorator;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class ValidacaoFontePagadora extends Decorator<GerenteFontePagadora> {

	public ValidacaoFontePagadora(GerenteFontePagadora innerComponent) {
		super(innerComponent);
	}

	@Service
	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		Validacao.obrigatorio(fonte.getNome(), "O campo nome é obrigatório");
		Validacao.obrigatorio(fonte.getCpfCnpj(),
				"O campo CPF/CNPJ é obrigatório");
		Validacao.numeroDifetenteZero(fonte.getRendimentosRecebidos(),
				"O campo rendimentos recebidos é obrigatório");
		Validacao.numeroMaiorQueZero(fonte.getRendimentosRecebidos(),
				"O campo rendimentos recebidos deve ser maior que zero");
		Validacao.cpfOuCnpj(fonte.getCpfCnpj(), "O campo CPF/CNPJ é inválido");
		getInnerComponent().criarFontePagadora(titular, fonte);
	}
}
