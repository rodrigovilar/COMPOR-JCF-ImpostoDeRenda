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

		if (baseCalculo < 2453.51 * 12) {
			return impostoDevidoFaixa2(baseCalculo);
		} 

		if (baseCalculo < 3271.39 * 12) {
			return impostoDevidoFaixa3(baseCalculo);
		} 

		if (baseCalculo < 4087.66 * 12) {
			return impostoDevidoFaixa4(baseCalculo);
		} 
		
		return impostoDevidoFaixa5(baseCalculo);
	}
	
	private double impostoDevidoFaixa2(double totalRecebido) {
		return calculoGenerico(totalRecebido, 0.07500, 122.78 * 12);
	}

	private double impostoDevidoFaixa3(double totalRecebido) {
		return calculoGenerico(totalRecebido, 0.1500, 306.80 * 12);
	}

	private double impostoDevidoFaixa4(double totalRecebido) {
		return calculoGenerico(totalRecebido, 0.22500, 552.15 * 12);
	}

	private double impostoDevidoFaixa5(double totalRecebido) {
		return calculoGenerico(totalRecebido, 0.27500, 756.53 * 12);
	}

	private double calculoGenerico(double totalRecebido, double taxa,
			double parcelaADeduzir) {
		return (totalRecebido * taxa) - parcelaADeduzir;
	}

}