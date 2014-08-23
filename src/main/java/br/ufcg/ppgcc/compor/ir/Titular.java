package br.ufcg.ppgcc.compor.ir;

/**
 * Representa os dados de um titular de uma declaração de imposto de rende
 */
public class Titular extends Pessoa {

	private String tituloEleitoral;
	private Endereco endereco;
	private int naturezaOcupacao;
	private int ocupacaoPrincipal;

	public String getTituloEleitoral() {
		return tituloEleitoral;
	}

	public void setTituloEleitoral(String tituloEleitoral) {
		this.tituloEleitoral = tituloEleitoral;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getNaturezaOcupacao() {
		return naturezaOcupacao;
	}

	public void setNaturezaOcupacao(int naturezaOcupacao) {
		this.naturezaOcupacao = naturezaOcupacao;
	}

	public int getOcupacaoPrincipal() {
		return ocupacaoPrincipal;
	}

	public void setOcupacaoPrincipal(int ocupacaoPrincipal) {
		this.ocupacaoPrincipal = ocupacaoPrincipal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + super.hashCode();
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + naturezaOcupacao;
		result = prime * result + ocupacaoPrincipal;
		result = prime * result
				+ ((tituloEleitoral == null) ? 0 : tituloEleitoral.hashCode());
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
		Titular other = (Titular) obj;
		if (!super.equals(other)) {
			return false;
		}
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (naturezaOcupacao != other.naturezaOcupacao)
			return false;
		if (ocupacaoPrincipal != other.ocupacaoPrincipal)
			return false;
		if (tituloEleitoral == null) {
			if (other.tituloEleitoral != null)
				return false;
		} else if (!tituloEleitoral.equals(other.tituloEleitoral))
			return false;
		return true;
	}

}
