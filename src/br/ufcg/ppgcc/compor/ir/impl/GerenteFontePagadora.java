package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class GerenteFontePagadora extends Component {

	private Map<Titular, List<FontePagadora>> fontesPagadoras = 
			new HashMap<Titular, List<FontePagadora>>();

	public GerenteFontePagadora() {
		super("Gerente de fontes pagadoras");
	}

	public void criarFontePagadora(Titular titular,
			FontePagadora fontePagadora) {
		inicializarLista(titular);
		fontesPagadoras.get(titular).add(fontePagadora);
	}

	@Service
	public List<FontePagadora> listarFontesPagadoras(Titular titular) {
		inicializarLista(titular);
		return fontesPagadoras.get(titular);
	}
	
	private void inicializarLista(Titular titular) {
		if (fontesPagadoras.get(titular) == null) {
			fontesPagadoras.put(titular, new ArrayList<FontePagadora>());
		}
	}

	@Service
	public Double totalRecebido(Titular titular) {
		List<FontePagadora> fontes = listarFontesPagadoras(titular);
		double soma = 0.0;
		
		for (FontePagadora fontePagadora : fontes) {
			soma += fontePagadora.getRendimentosRecebidos();
		}
		
		return soma;
	}	

	@Service
	public Double totalPago(Titular titular) {
		List<FontePagadora> fontes = listarFontesPagadoras(titular);
		double soma = 0.0;
		
		for (FontePagadora fontePagadora : fontes) {
			soma += fontePagadora.getImpostoPago();
		}
		
		return soma;
	}
}