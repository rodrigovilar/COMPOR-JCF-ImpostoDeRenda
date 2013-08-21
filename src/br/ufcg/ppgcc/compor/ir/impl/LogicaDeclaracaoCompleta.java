package br.ufcg.ppgcc.compor.ir.impl;

import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class LogicaDeclaracaoCompleta {

	public Resultado declaracaoCompleta(Titular titular) {
		double totalRecebido = LogicaFontePagadora.getInstance().totalRecebido(titular);
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
