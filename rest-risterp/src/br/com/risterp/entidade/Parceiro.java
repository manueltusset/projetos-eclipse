package br.com.risterp.entidade;

import java.util.Date;

public class Parceiro {

	private int cparceiro;
	private String razao_social;
	private String cnpj;
	private double valor_contratado;
	private Date vencimento;
	private Date dia_inicio;
	private String email;
	private String telefone;
	private Date ultimo_pagamento;
	private int dias_ultimo_pagamento;

	public int getDias_ultimo_pagamento() {
		return dias_ultimo_pagamento;
	}

	public void setDias_ultimo_pagamento(int dias_ultimo_pagamento) {
		this.dias_ultimo_pagamento = dias_ultimo_pagamento;
	}

	public Date getUltimo_pagamento() {
		return ultimo_pagamento;
	}

	public void setUltimo_pagamento(Date ultimo_pagamento) {
		this.ultimo_pagamento = ultimo_pagamento;
	}

	public int getCparceiro() {
		return cparceiro;
	}

	public void setCparceiro(int cparceiro) {
		this.cparceiro = cparceiro;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public double getValor_contratado() {
		return valor_contratado;
	}

	public void setValor_contratado(double valor_contratado) {
		this.valor_contratado = valor_contratado;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getDia_inicio() {
		return dia_inicio;
	}

	public void setDia_inicio(Date dia_inicio) {
		this.dia_inicio = dia_inicio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
