package br.ufcg.ppgcc.compor.ir.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;


public class LogicaFontePagadora {

	private Map<Titular, List<FontePagadora>> fontes = new HashMap<Titular, List<FontePagadora>>();

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		if (fontes.get(titular) == null) {
			fontes.put(titular, new ArrayList<FontePagadora>());
		}

		fontes.get(titular).add(fonte);
	}

	public List<FontePagadora> getFontes(Titular titular) {
		if (fontes.get(titular) == null) {
			fontes.put(titular, new ArrayList<FontePagadora>());
		}

		return fontes.get(titular);
	}

}
