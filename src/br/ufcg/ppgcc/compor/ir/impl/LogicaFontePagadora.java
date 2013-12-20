package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class LogicaFontePagadora {

	private static LogicaFontePagadora instance;

	private Map<Titular, List<FontePagadora>> fontes = new HashMap<Titular, List<FontePagadora>>();

	public static LogicaFontePagadora getInstance() {
		if (instance == null) {
			instance = new LogicaFontePagadora();
		}
		return instance;
	}

	private LogicaFontePagadora() {
	}

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		LogicaAuditoria.getInstancia().iniciarTransacao("Criação da fonte pagadora " + fonte.getNome());
		LogicaAutenticacao.getInstancia().verificarLogin();

		Validacao.obrigatorio(fonte.getNome(), "O campo nome é obrigatório");
		Validacao.obrigatorio(fonte.getCpfCnpj(),
				"O campo CPF/CNPJ é obrigatório");
		Validacao.numeroDiferenteZero(fonte.getRendimentosRecebidos(),
				"O campo rendimentos recebidos é obrigatório");
		Validacao.numeroMaiorQueZero(fonte.getRendimentosRecebidos(),
				"O campo rendimentos recebidos deve ser maior que zero");
		Validacao.cpfOuCnpj(fonte.getCpfCnpj(), "O campo CPF/CNPJ é inválido");
		ValidacaoTitular.validar(titular);

		inicializaLista(titular);
		fontes.get(titular).add(fonte);
		LogicaAuditoria.getInstancia().concluirTransacao();
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

	public double totalRecebido(Titular titular) {
		List<FontePagadora> fontes = getFontes(titular);
		double soma = 0.0;

		for (FontePagadora fontePagadora : fontes) {
			soma += fontePagadora.getRendimentosRecebidos();
		}

		return soma;
	}

	public double totalPago(Titular titular) {
		List<FontePagadora> fontes = getFontes(titular);
		double soma = 0.0;

		for (FontePagadora fontePagadora : fontes) {
			soma += fontePagadora.getImpostoPago();
		}

		return soma;
	}

	public void limpar() {
		fontes.clear();
	}
}
