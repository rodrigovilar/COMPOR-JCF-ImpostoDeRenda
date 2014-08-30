package br.ufcg.ppgcc.compor.ir;


/**
 * Há uma dedução de R$ 1974,72 para cada dependente cadastrado
 * 
 */
public class Dependente extends Pessoa {

	private int tipo;
	
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + super.hashCode();
		result = prime * result + tipo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dependente other = (Dependente) obj;
		if (!super.equals(other)) {
			return false;
		}
		if (tipo != other.tipo)
			return false;
		return true;
	}

}
