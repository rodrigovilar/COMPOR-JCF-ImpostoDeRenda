package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.Pessoa;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class GerenteDependente extends Component {

	private Map<Titular, List<Dependente>> dependentes = new HashMap<Titular, List<Dependente>>();

	public GerenteDependente() {
		super("Gerente de dependentes");
	}

	public void criarDependente(Titular titular, Dependente dependente) {
		inicializarLista(titular);
		dependentes.get(titular).add(dependente);
	}

	@Service
	public List<Dependente> listarDependentes(Titular titular) {
		inicializarLista(titular);
		return dependentes.get(titular);
	}

	public List<Dependente> listarTodosDependentes() {
		List<Dependente> resultado = new ArrayList<Dependente>();
		
		for (Titular titular : dependentes.keySet()) {
			resultado.addAll(dependentes.get(titular));
		}
		
		return resultado;
	}

	private void inicializarLista(Titular titular) {
		if (dependentes.get(titular) == null) {
			dependentes.put(titular, new ArrayList<Dependente>());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Service(requiredServices="listarTitulares")
	public void validarPessoa(Pessoa pessoa) {
		List<Titular> titulares = (List<Titular>) requestService("listarTitulares");
		List<Dependente> dependentes = listarTodosDependentes();
		
		if (!titulares.contains(pessoa) && !dependentes.contains(pessoa)) {
			throw new ExcecaoImpostoDeRenda("Pessoa não cadastrada");
		}
	}
}