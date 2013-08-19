package br.ufcg.ppgcc.compor.ir.fachada;

public class FontePagadora {

	private String nome;
	private String cpfCnpj;
	private double rendimentosRecebidos;
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

	public double getRendimentosRecebidos() {
		return rendimentosRecebidos;
	}

	public void setRendimentosRecebidos(double rendimentosRecebidos) {
		this.rendimentosRecebidos = rendimentosRecebidos;
	}

	public void setImpostoPago(double impostoPago) {
		this.impostoPago = impostoPago;
	}

	public double getImpostoPago() {
		return impostoPago;
	}
}
