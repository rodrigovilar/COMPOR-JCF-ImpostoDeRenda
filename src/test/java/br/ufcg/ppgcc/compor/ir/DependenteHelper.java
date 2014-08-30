package br.ufcg.ppgcc.compor.ir;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;

public class DependenteHelper {

	static Dependente criarDependentePadrao1() {
		return criarDependente("000.000.000-00", Calendar.getInstance(), "Filho 1", 21);
	}

	static Dependente criarDependentePadrao2() {
		return criarDependente("111.111.111-11", Calendar.getInstance(), "Filho 2", 21);
	}

	static Dependente criarDependente(String cpf, Calendar dataNascimento,
			String nome, int tipo) {
		Dependente dependente = new Dependente();
		dependente.setCpf(cpf);
		dependente.setDataNascimento(dataNascimento);
		dependente.setNome(nome);
		dependente.setTipo(tipo);
		return dependente;
	}
	
	static void verificaCriacaoDependentes(FachadaExperimento fachada, Titular titular, Dependente... dependentes) {
		for (Dependente dependente : dependentes) {
			fachada.criarDependente(titular, dependente);
		}
	
		List<Dependente> dependentesSalvos = fachada.listarDependentes(titular);
		assertEquals(dependentes.length, dependentesSalvos.size());
		
		for (int i = 0; i < dependentes.length; i++) {
			assertEquals(dependentes[i], dependentesSalvos.get(i));
		}
		
	}

	static void excecaoCriarDependente(FachadaExperimento fachada, Titular titular, Dependente dependente, String mensagem) {
		try {
			fachada.criarDependente(titular, dependente);
			Assert.fail("A criação de dependente deveria ter lançado exceção");
		} catch (ExcecaoImpostoDeRenda e) {
			Assert.assertEquals(e.getMessage(), mensagem);
		}
	}

}
