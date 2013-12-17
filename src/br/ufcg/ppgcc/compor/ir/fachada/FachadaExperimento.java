package br.ufcg.ppgcc.compor.ir.fachada;

import java.util.List;

public interface FachadaExperimento {

	void criarNovoTitular(Titular titular);

	List<Titular> listarTitulares();

	void criarFontePagadora(Titular titular, FontePagadora fonte);

	List<FontePagadora> listarFontes(Titular titular);

	void criarDependente(Titular titular, Dependente dependente);

	List<Dependente> listarDependentes(Titular titular);

	Resultado declaracaoCompleta(Titular titular);

	void login(String login, String senha);

	void criarUsuario(String login, String senha);

	void criarGastoDedutivel(Pessoa pessoa, GastoDedutivel gasto);

	List<GastoDedutivel> listarGastosDedutiveis(Pessoa pessoa);

}
