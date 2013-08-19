package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.List;

import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class LogicaTitular {
	
	private static LogicaTitular instance;

	private List<Titular> titulares = new ArrayList<Titular>();

	public static LogicaTitular getInstance() {
		if (instance == null) {
			instance = new LogicaTitular();
		}
		return instance;
	}
	
	private LogicaTitular(){}
	
	public void criarNovoTitular(Titular titular) {
		Validacao.obrigatorio(titular.getNome(), "O campo nome é obrigatório");
		Validacao.obrigatorio(titular.getCpf(), "O campo CPF é obrigatório");
		Validacao.cpf(titular.getCpf(), "O campo CPF está inválido");
		titulares.add(titular);
	}

	public List<Titular> listarTitulares() {
		return titulares;
	}

	public void limpar() {
		titulares.clear();
	}

}
