package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel;
import br.ufcg.ppgcc.compor.ir.fachada.Pessoa;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel.TipoGasto;

public class LogicaGastoDedutivel {
	
	private static LogicaGastoDedutivel instance; 
	
	private Map<Pessoa, List<GastoDedutivel>> gastos = new HashMap<Pessoa, List<GastoDedutivel>>();

	private LogicaGastoDedutivel() {}
	
	public static LogicaGastoDedutivel getInstancia() {
		if (instance == null) {
			instance = new LogicaGastoDedutivel();
		}
		return instance;
	}

	public void criarGastoDedutivel(Pessoa realizador, GastoDedutivel gasto) {
		LogicaAuditoria.getInstancia().iniciarTransacao("Criação do gasto " + gasto.getTipo());
		LogicaAutenticacao.getInstancia().verificarLogin();

		Validacao.obrigatorio(gasto.getCnpjCpfReceptor(), "O campo CPF/CNPJ do receptor é obrigatório");
		Validacao.obrigatorio(gasto.getTipo(), "O campo tipo é obrigatório");
		Validacao.numeroDiferenteZero(gasto.getValor(),
				"O campo valor é obrigatório");
		Validacao.numeroMaiorQueZero(gasto.getValor(),
				"O campo valor deve ser maior que zero");
		ValidacaoTitular.validarPessoa(realizador);

		if (gastos.get(realizador) == null) {
			gastos.put(realizador, new ArrayList<GastoDedutivel>());
		}

		gastos.get(realizador).add(gasto);
		LogicaAuditoria.getInstancia().concluirTransacao();
	}

	public List<GastoDedutivel> listarGastosDedutiveis(Pessoa realizador) {
		if (gastos.get(realizador) == null) {
			gastos.put(realizador, new ArrayList<GastoDedutivel>());
		}
		
		return gastos.get(realizador);
	}

	public void limpar() {
		gastos.clear();
	}

	public List<GastoDedutivel> getGastosEducacao(Titular titular,
			List<Dependente> dependentes) {
		return getGastos(titular, dependentes, TipoGasto.Educacao);
	}

	public List<GastoDedutivel> getGastosSaude(Titular titular,
			List<Dependente> dependentes) {
		return getGastos(titular, dependentes, TipoGasto.Saude);
	}

	private List<GastoDedutivel> getGastos(Titular titular,
			List<Dependente> dependentes, TipoGasto tipo) {
		List<GastoDedutivel> resultado = new ArrayList<GastoDedutivel>();
		
		for (Pessoa pessoa : gastos.keySet()) {
			if (pessoa.equals(titular) || dependentes.contains(pessoa)) {
				
				List<GastoDedutivel> gastosDedutiveis = gastos.get(pessoa);
				for (GastoDedutivel gastoDedutivel : gastosDedutiveis) {
					if (gastoDedutivel.getTipo().equals(tipo)) {
						resultado.add(gastoDedutivel);
					}
				}
				
			}
		}
		
		return resultado;
	}

}
