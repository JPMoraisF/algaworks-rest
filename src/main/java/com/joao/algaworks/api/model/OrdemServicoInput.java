package com.joao.algaworks.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrdemServicoInput {

	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	//aqui eu poderia colocar só o valor do ID do cliente. Nesse caso, não
	//precisaria criar um novo objeto com o valor do id do cliente
	@Valid
	@NotNull
	private ClienteIdInput cliente;

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public ClienteIdInput getCliente() {
		return cliente;
	}

	public void setCliente(ClienteIdInput cliente) {
		this.cliente = cliente;
	} 
	
	
	
}
