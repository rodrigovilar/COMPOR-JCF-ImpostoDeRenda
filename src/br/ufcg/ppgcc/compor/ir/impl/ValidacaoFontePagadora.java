package br.ufcg.ppgcc.compor.ir.impl;

import java.util.List;

import net.compor.frameworks.jcf.api.Decorator;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class ValidacaoFontePagadora extends Decorator<GerenteFontePagadora> {

	public ValidacaoFontePagadora(GerenteFontePagadora innerComponent) {
		super(innerComponent);
	}

	@Service(requiredServices="listarTitulares")
	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		Validacao.obrigatorio(fonte.getNome(), "O campo nome é obrigatório");
		Validacao.obrigatorio(fonte.getCpfCnpj(),
				"O campo CPF/CNPJ é obrigatório");
		Validacao.numeroDifetenteZero(fonte.getRendimentosRecebidos(),
				"O campo rendimentos recebidos é obrigatório");
		Validacao.numeroMaiorQueZero(fonte.getRendimentosRecebidos(),
				"O campo rendimentos recebidos deve ser maior que zero");
		Validacao.cpfOuCnpj(fonte.getCpfCnpj(), "O campo CPF/CNPJ é inválido");
		validar(titular);

		getInnerComponent().criarFontePagadora(titular, fonte);
	}

	@SuppressWarnings("unchecked")
	private void validar(Titular titular) {
		List<Titular> titulares = (List<Titular>) requestService("listarTitulares");
		if (!titulares.contains(titular)) {
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
		}
	}
}
