package br.ufcg.ppgcc.compor.ir.impl;

import java.util.HashMap;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufcg.ppgcc.compor.ir.fachada.ExcecaoImpostoDeRenda;

public class GerenteAutenticacao extends Component{
	
	private static GerenteAutenticacao instance;

	private Map<String,String> logins = new HashMap<String, String>();

	public static GerenteAutenticacao getInstancia() {
		if (instance == null) {
			instance = new GerenteAutenticacao();
		}
		return instance;
	}
	
	public GerenteAutenticacao() {
		super("Gerente de autenticacao");
		logins.put("admin", "admin");
	}

	@Service
	public void login(String login, String senha) {
		if (!logins.containsKey(login)) {
			throw new ExcecaoImpostoDeRenda("Login desconhecido");
		}
		
		String senhaCadastrada = logins.get(login);
		if (senhaCadastrada == null) {
			if (senha != null) {
				throw new ExcecaoImpostoDeRenda("Senha errada");
			} 
		} else {
			if (!senhaCadastrada.equals(senha)) {
				throw new ExcecaoImpostoDeRenda("Senha errada");
			} 			
		}
	}
}
