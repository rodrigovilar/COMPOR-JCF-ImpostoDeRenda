package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class LogicaFontePagadora {

	private Map<Titular, List<FontePagadora>> fontes = new HashMap<Titular, List<FontePagadora>>();

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		Validacao.obrigatorio(fonte.getNome(), "O campo nome é obrigatório");
		Validacao.obrigatorio(fonte.getCpfCnpj(),
				"O campo CPF/CNPJ é obrigatório");
		Validacao.numeroDiferenteZero(fonte.getRendimentosRecebidos(),
				"O campo rendimentos recebidos é obrigatório");
		Validacao.numeroMaiorQueZero(fonte.getRendimentosRecebidos(),
				"O campo rendimentos recebidos deve ser maior que zero");
		Validacao.cpfOuCnpj(fonte.getCpfCnpj(), "O campo CPF/CNPJ é inválido");
		validar(titular);

		inicializaLista(titular);
		fontes.get(titular).add(fonte);
	}

	private void validar(Titular titular) {
		if(!LogicaTitular.getInstance().listarTitulares().contains(titular)) {
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
		}
	}

	private void inicializaLista(Titular titular) {
		if (fontes.get(titular) == null) {
			fontes.put(titular, new ArrayList<FontePagadora>());
		}
	}

	public List<FontePagadora> getFontes(Titular titular) {
		inicializaLista(titular);
		return fontes.get(titular);
	}

}
