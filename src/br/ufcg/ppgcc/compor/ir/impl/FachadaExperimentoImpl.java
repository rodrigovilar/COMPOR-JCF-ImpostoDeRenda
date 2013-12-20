package br.ufcg.ppgcc.compor.ir.impl;

import java.util.List;

import br.ufcg.ppgcc.compor.ir.fachada.Auditor;
import br.ufcg.ppgcc.compor.ir.fachada.Dependente;
import br.ufcg.ppgcc.compor.ir.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.ir.fachada.GastoDedutivel;
import br.ufcg.ppgcc.compor.ir.fachada.NovoAuditor;
import br.ufcg.ppgcc.compor.ir.fachada.Pessoa;
import br.ufcg.ppgcc.compor.ir.fachada.Resultado;
import br.ufcg.ppgcc.compor.ir.fachada.Titular;

public class FachadaExperimentoImpl implements FachadaExperimento {

	private LogicaAutenticacao logicaAutenticacao = LogicaAutenticacao.getInstancia();
	private LogicaTitular logicaTitular = LogicaTitular.getInstance();
	private LogicaFontePagadora logicaFontePagadora = LogicaFontePagadora
			.getInstance();
	private LogicaDependente logicaDependente = LogicaDependente.getInstancia();
	private LogicaDeclaracaoCompleta logicaDeclaracaoCompleta = new LogicaDeclaracaoCompleta();
	private LogicaGastoDedutivel logicaGastoDedutivel = LogicaGastoDedutivel.getInstancia();
	private NovaLogicaAuditoria logicaAuditoria = NovaLogicaAuditoria.getInstancia();

	public FachadaExperimentoImpl() {
		logicaTitular.limpar();
		logicaFontePagadora.limpar();
		logicaDependente.limpar();
		logicaAutenticacao.limpar();
		logicaAuditoria.limpar();
	}

	public FachadaExperimentoImpl(boolean usarAutenticacao) {
		this();
		logicaAutenticacao.setUsarAutenticacao(usarAutenticacao);
	}

	public void criarNovoTitular(Titular titular) {
		logicaTitular.criarNovoTitular(titular);
	}

	public List<Titular> listarTitulares() {
		return logicaTitular.listarTitulares();
	}

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		logicaFontePagadora.criarFontePagadora(titular, fonte);
	}

	public List<FontePagadora> listarFontes(Titular titular) {
		return logicaFontePagadora.getFontes(titular);
	}

	@Override
	public void criarDependente(Titular titular, Dependente dependente) {
		logicaDependente.criarDependente(titular, dependente);
	}

	@Override
	public List<Dependente> listarDependentes(Titular titular) {
		return logicaDependente.getDependentes(titular);
	}

	@Override
	public Resultado declaracaoCompleta(Titular titular) {
		return logicaDeclaracaoCompleta.declaracaoCompleta(titular);
	}

	@Override
	public void login(String login, String senha) {
		logicaAutenticacao.login(login, senha);
	}

	@Override
	public void criarUsuario(String login, String senha) {
		logicaAutenticacao.criarUsuario(login, senha);
	}

	@Override
	public void criarGastoDedutivel(Pessoa pessoa, GastoDedutivel gasto) {
		logicaGastoDedutivel.criarGastoDedutivel(pessoa, gasto);
	}

	@Override
	public List<GastoDedutivel> listarGastosDedutiveis(Pessoa pessoa) {
		return logicaGastoDedutivel.listarGastosDedutiveis(pessoa);
	}

	@Override
	public void setAuditor(Auditor auditor) {
		logicaAuditoria.setAuditor((NovoAuditor) auditor);
	}
}
