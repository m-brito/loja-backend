package io.github.mbrito.vendas.casoDeUso.pedido.dto;

public class RequestItemPedidoDTO {
	private Integer produto;
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