package io.github.mbrito.vendas.casoDeUso.pedido.dto;

import java.math.BigDecimal;
import java.util.List;

public class RequestPedidoDTO {
	private Integer cliente;
	private BigDecimal total;
	private List<RequestItemPedidoDTO> items;	
	
	public RequestPedidoDTO() {}

	public RequestPedidoDTO(Integer cliente, BigDecimal total, List<RequestItemPedidoDTO> items) {
		super();
		this.cliente = cliente;
		this.total = total;
		this.items = items;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<RequestItemPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<RequestItemPedidoDTO> items) {
		this.items = items;
	}
	
	
}
