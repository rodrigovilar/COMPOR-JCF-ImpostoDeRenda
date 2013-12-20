package br.ufcg.ppgcc.compor.ir.impl;

import java.util.HashMap;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.Auditor;
import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;

public class GerenteAutenticacao extends Component{
	
	private Map<String,String> logins = new HashMap<String, String>();
	private String logado = null;
	private boolean usarAutenticacao;
	private Auditor auditor;
	private int transacao = 0;

	public GerenteAutenticacao() {
		super("Gerente de autenticacao");
		logins.put("admin", "admin");
	}

	@Service
	public void setUsarAutenticacao(Boolean usarAutenticacao) {
		this.usarAutenticacao = usarAutenticacao;
	}

	@Service
	public Boolean isUsarAutenticacao() {
		return usarAutenticacao;
	}

	public String getLogado() {
		return logado;
	}

	@Service(requiredServices="iniciarTransacaoSemUsuario,abortarTransacao,concluirTransacao")
	public void login(String login, String senha) {
		if ("admin".equals(login)) {
			iniciarTransacaoSemUsuario("Login default");
			
		} else {
			iniciarTransacaoSemUsuario("Login " + login);
		}
		
		if (!logins.containsKey(login)) {
			abortarTransacao("Usuário desconhecido");
			throw new ExcecaoImpostoDeRenda("Login desconhecido");
		}
		
		String senhaCadastrada = logins.get(login);
		if (senhaCadastrada == null) {
			if (senha != null) {
				abortarTransacao("Senha errada");
				throw new ExcecaoImpostoDeRenda("Senha errada");
			} 
		} else {
			if (!senhaCadastrada.equals(senha)) {
				abortarTransacao("Senha errada");
				throw new ExcecaoImpostoDeRenda("Senha errada");
			} 			
		}
		
		logado = login;
		concluirTransacao();
	}
	
	@Service
	public void verificarLogin() {
		if (usarAutenticacao && logado == null) {
			abortarTransacao("Usuário não logado");
			throw new ExcecaoImpostoDeRenda("Usuário não logado");
		}
	}

	@Service
	public void criarUsuario(String login, String senha) {
		iniciarTransacao("Criação do usuário " + login);
		verificarLogin();
		logins.put(login, senha);
		concluirTransacao();
	}
	
	@Service
	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	@Service
	public void iniciarTransacao(String descricao) {

		if (usarAutenticacao && auditor != null) {
			auditor.iniciandoTransacao(++transacao, logado, descricao);
		}
	}

	@Service
	public void iniciarTransacaoSemUsuario(String descricao) {
		
		if (usarAutenticacao && auditor != null) {
			auditor.iniciandoTransacao(++transacao, null, descricao);
		}
	}

	@Service
	public void abortarTransacao(String erro) {
		
		if (usarAutenticacao && auditor != null) {
			auditor.transacaoAbortada(transacao, erro);
		}
	}

	@Service
	public void concluirTransacao() {
		
		if (usarAutenticacao && auditor != null) {
			auditor.transacaoConcluida(transacao);
		}
	}

}
