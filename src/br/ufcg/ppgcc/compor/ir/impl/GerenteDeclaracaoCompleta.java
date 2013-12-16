package br.ufcg.ppgcc.compor.ir.impl;

import java.util.List;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class GerenteDeclaracaoCompleta extends Component {

	public GerenteDeclaracaoCompleta() {
		super("Gerente de declaração completa");
	}

	@SuppressWarnings("unchecked")
	@Service(requiredServices = "totalPago,totalRecebido,listarDependentes,verificarLogin")
	public Resultado declaracaoCompleta(Titular titular) {
		requestService("verificarLogin");
		List<Dependente> dependentes = (List<Dependente>) requestService(
				"listarDependentes", titular);

		double totalRecebido = (Double) requestService("totalRecebido", titular);
		double baseCalculo = descontoDependentes(totalRecebido, dependentes);
		double impostoDevido = impostoDevido(baseCalculo);
		double impostoPago = (Double) requestService("totalPago", titular);

		Resultado resultado = new Resultado();
		resultado.setImpostoDevido(impostoDevido);
		resultado.setImpostoPago(impostoPago);
		resultado.setImpostoAPagar(impostoDevido - impostoPago); 
		return resultado;
	}

	public double impostoDevido(double baseCalculo) {
		if (baseCalculo < 1637.11 * 12) { // isento
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

	public double descontoDependentes(double totalRecebido,
			List<Dependente> dependentes) {
		return Math.max(0, totalRecebido - (dependentes.size() * 1974.72));
	}
}