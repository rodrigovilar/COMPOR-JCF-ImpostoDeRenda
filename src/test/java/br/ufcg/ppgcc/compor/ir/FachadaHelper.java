package br.ufcg.ppgcc.compor.ir;


import org.junit.Assert;

public class FachadaHelper {

	public static FachadaExperimento criarFachada() {
		
		FachadaExperimento fachada = null;
		
		try {
			@SuppressWarnings("unchecked")
			Class<FachadaExperimento> clazz = (Class<FachadaExperimento>) Class
					.forName("br.ufcg.ppgcc.compor.ir.impl.ImpostoDeRenda");
			
			fachada = clazz.newInstance();
			
		} catch (ClassNotFoundException e) {
			Assert.fail();
		} catch (InstantiationException e) {
			Assert.fail();
		} catch (IllegalAccessException e) {
			Assert.fail();
		}
		
		return fachada;
	}

}
