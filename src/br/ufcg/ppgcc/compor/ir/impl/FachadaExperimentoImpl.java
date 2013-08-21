package br.ufcg.ppgcc.compor.ir.impl;

import java.util.List;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class FachadaExperimentoImpl extends ComporFacade implements
		FachadaExperimento {

	@Override
	protected void addComponents() {
		GerenteTitular gerenteTitular = new GerenteTitular();
		add(gerenteTitular);
		add(new ValidacaoTitular(gerenteTitular));
		
		GerenteFontePagadora gerenteFonte = new GerenteFontePagadora();
		add(gerenteFonte);
		add(new ValidacaoFontePagadora(gerenteFonte));
		
		GerenteDependente gerenteDependente = new GerenteDependente();
		add(gerenteDependente);
		add(new ValidacaoDependente(gerenteDependente));
		
		add(new GerenteDeclaracaoCompleta());
	}

	public void criarNovoTitular(Titular titular) {
		requestService("criarTitular", titular);
	}

	@SuppressWarnings("unchecked")
	public List<Titular> listarTitulares() {
		return (List<Titular>) requestService("listarTitulares");
	}
	
	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		requestService("criarFontePagadora", titular, fonte);
	}

	@SuppressWarnings("unchecked")
	public List<FontePagadora> listarFontes(Titular titular) {
		return (List<FontePagadora>) requestService("listarFontesPagadoras",
				titular);
	}

	public void criarDependente(Titular titular, Dependente dependente) {
		requestService("criarDependente", titular, dependente);
	}

	@SuppressWarnings("unchecked")
	public List<Dependente> listarDependentes(Titular titular) {
		return (List<Dependente>) requestService("listarDependentes",
				titular);
	}

	@Override
	public Resultado declaracaoCompleta(Titular titular) {
		return (Resultado) requestService("declaracaoCompleta", titular);
	}

}
