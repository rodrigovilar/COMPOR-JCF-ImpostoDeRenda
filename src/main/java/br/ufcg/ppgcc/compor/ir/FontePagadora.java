package br.ufcg.ppgcc.compor.ir;

public class FontePagadora {

	private String nome;
	private String cpfCnpj;
	private double rendimentoRecebidos;
	private double impostoPago;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public double getRendimentoRecebidos() {
		return rendimentoRecebidos;
	}

	public void setRendimentoRecebidos(double rendimentoRecebidos) {
		this.rendimentoRecebidos = rendimentoRecebidos;
	}

	public void setImpostoPago(double impostoPago) {
		this.impostoPago = impostoPago;
	}

	public double getImpostoPago() {
		return impostoPago;
	}
}
