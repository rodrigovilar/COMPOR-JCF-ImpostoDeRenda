package br.ufcg.ppgcc.compor.ir.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class GerenteDeclaracaoCompleta extends Component {
	
	public GerenteDeclaracaoCompleta() {
		super("Gerente de declaração completa");
	}

	@Service(requiredServices="totalRecebido")
	public Resultado declaracaoCompleta(Titular titular) {
		double totalRecebido = (Double) requestService("totalRecebido", titular);
		double impostoDevido = impostoDevido(totalRecebido);

		Resultado resultado = new Resultado();
		resultado.setImpostoDevido(impostoDevido);
		return resultado;
	}

	public double impostoDevido(double baseCalculo) {		
		if (baseCalculo < 1637.11 * 12) { //isento
			return 0.0; 
		} 

		return 0.0; 
	}

}