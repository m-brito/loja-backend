package io.github.mbrito.vendas.casoDeUso.pedido.dto;

import jakarta.validation.constraints.NotNull;

public class RequestItemPedidoDTO {
	
	@NotNull(message = "{produto.not.null}")
	private Integer produto;
	
	@NotNull(message = "{quantidade.not.null}")
	private Integer quantidade;
	
	public RequestItemPedidoDTO() {}

	public RequestItemPedidoDTO(Integer produto, Integer quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Integer getProduto() {
		return produto;
	}

	public void setProduto(Integer produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
