package br.ufcg.ppgcc.compor.ir.impl;

import java.util.List;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel;
import br.ufcg.ppgcc.compor.ir.fachada.Pessoa;
import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class FachadaExperimentoImpl extends ComporFacade implements
		FachadaExperimento {
	
	public FachadaExperimentoImpl() {}
	
	public FachadaExperimentoImpl(boolean usarAutenticacao) {
		this();
		requestService("setUsarAutenticacao", Boolean.valueOf(usarAutenticacao));
	}

	@Override
	protected void addComponents() {
		add(new GerenteAutenticacao());
		
		GerenteTitular gerenteTitular = new GerenteTitular();
		add(gerenteTitular);
		add(new ValidacaoTitular(gerenteTitular));
		
		GerenteFontePagadora gerenteFonte = new GerenteFontePagadora();
		add(gerenteFonte);
		add(new ValidacaoFontePagadora(gerenteFonte));
		
		GerenteDependente gerenteDependente = new GerenteDependente();
		add(gerenteDependente);
		add(new ValidacaoDependente(gerenteDependente));

		GerenteGastoDedutivel gerenteGastoDedutivel = new GerenteGastoDedutivel();
		add(gerenteGastoDedutivel);
		add(new ValidacaoGastoDedutivel(gerenteGastoDedutivel));

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

	@Override
	public void login(String login, String senha) {
		requestService("login", login, senha);
	}

	@Override
	public void criarUsuario(String login, String senha) {
		requestService("criarUsuario", login, senha);
	}

	@Override
	public void criarGastoDedutivel(Pessoa pessoa, GastoDedutivel gasto) {
		requestService("criarGastoDedutivel", pessoa, gasto);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<GastoDedutivel> listarGastosDedutiveis(Pessoa pessoa) {
		return (List<GastoDedutivel>) requestService("listarGastosDedutiveis",
				pessoa);
	}

}
