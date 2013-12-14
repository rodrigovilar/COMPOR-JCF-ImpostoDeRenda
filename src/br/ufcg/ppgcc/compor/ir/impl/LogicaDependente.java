package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class LogicaDependente {
	
	private static LogicaDependente instance;

	private Map<Titular, List<Dependente>> dependentes = new HashMap<Titular, List<Dependente>>();

	public static LogicaDependente getInstancia() {
		if (instance == null) {
			instance = new LogicaDependente();
		}
		return instance;
	}
	
	private LogicaDependente(){}
	
	public void criarDependente(Titular titular, Dependente dependente) {
		Validacao.obrigatorio(dependente.getNome(), "O campo nome é obrigatório");
		Validacao.obrigatorio(dependente.getCpf(), "O campo CPF é obrigatório");
		Validacao.numeroDiferenteZero(dependente.getTipo(), "O campo tipo é obrigatório");
		Validacao.numeroMaiorQueZero(dependente.getTipo(), "O campo tipo é inválido");
		Validacao.cpf(dependente.getCpf(), "O campo CPF é inválido");
		ValidacaoTitular.validar(titular);

		inicializaLista(titular);
		dependentes.get(titular).add(dependente);
	}

	private void inicializaLista(Titular titular) {
		if (dependentes.get(titular) == null) {
			dependentes.put(titular, new ArrayList<Dependente>());
		}
	}

	public List<Dependente> getDependentes(Titular titular) {
		inicializaLista(titular);
		return dependentes.get(titular);
	}

	public void limpar() {
		dependentes.clear();		
	}

}
