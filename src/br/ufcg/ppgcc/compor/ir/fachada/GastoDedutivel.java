package br.ufcg.ppgcc.compor.ir.fachada;


public class GastoDedutivel {

	public enum TipoGasto { Educacao, Saude }

	
	private TipoGasto tipo;
	private double valor;
	private String cnpjCpfReceptor;


	public void setTipo(TipoGasto tipo) {
		this.tipo = tipo;
	}

	public TipoGasto getTipo() {
		return tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setCnpjCpfReceptor(String cnpjCpfReceptor) {
		this.cnpjCpfReceptor = cnpjCpfReceptor;
	}

	public String getCnpjCpfReceptor() {
		return cnpjCpfReceptor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cnpjCpfReceptor == null) ? 0 : cnpjCpfReceptor.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		GastoDedutivel other = (GastoDedutivel) obj;
		if (cnpjCpfReceptor == null) {
			if (other.cnpjCpfReceptor != null)
				return false;
		} else if (!cnpjCpfReceptor.equals(other.cnpjCpfReceptor))
			return false;
		if (tipo != other.tipo)
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}
}
