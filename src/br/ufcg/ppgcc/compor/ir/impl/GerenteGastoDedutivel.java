package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel.TipoGasto;
import br.ufcg.ppgcc.compor.ir.fachada.Pessoa;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class GerenteGastoDedutivel extends Component {

	private Map<Pessoa, List<GastoDedutivel>> gastos = new HashMap<Pessoa, List<GastoDedutivel>>();

	public GerenteGastoDedutivel() {
		super("Gerente de gastos dedutíveis");
	}

	public void criarGastoDedutivel(Pessoa pessoa, GastoDedutivel gasto) {
		inicializarLista(pessoa);
		gastos.get(pessoa).add(gasto);
	}

	@Service
	public List<GastoDedutivel> listarGastosDedutiveis(Pessoa pessoa) {
		inicializarLista(pessoa);
		return gastos.get(pessoa);
	}
	
	private void inicializarLista(Pessoa pessoa) {
		if (gastos.get(pessoa) == null) {
			gastos.put(pessoa, new ArrayList<GastoDedutivel>());
		}
	}

	@Service
	public List<GastoDedutivel> getGastosEducacao(Titular titular,
			List<Dependente> dependentes) {
		return getGastos(titular, dependentes, TipoGasto.Educacao);
	}

	@Service
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