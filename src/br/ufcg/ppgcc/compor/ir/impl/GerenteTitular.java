package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.List;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class GerenteTitular extends Component {
	
	private List<Titular> titulares = new ArrayList<Titular>();

	public GerenteTitular() {
		super("Gerente de titulares");
	}

	public void criarTitular(Titular titular) {
		titulares.add(titular);
	}
	
	@Service
	public List<Titular> listarTitulares() {
		return titulares;
	}
	
	@Service
	public void validarTitular(Titular titular) {
		List<Titular> titulares = listarTitulares();
		if (!titulares.contains(titular)) {
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
		}
	}

}