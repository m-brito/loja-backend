package io.github.mbrito.vendas.casoDeUso.pedido.dto;

import java.math.BigDecimal;
import java.util.List;

public class InformacoesPedidoDTO {
	private Integer id;
    private String nomeCliente;
    private BigDecimal total;
    private String dataPedido;
    private List<RequestItemPedidoDTO> items;
    
	public InformacoesPedidoDTO(Integer id, String nomeCliente, BigDecimal total, String dataPedido,
			List<RequestItemPedidoDTO> items) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.total = total;
		this.dataPedido = dataPedido;
		this.items = items;
	}
	
	public InformacoesPedidoDTO() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public List<RequestItemPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<RequestItemPedidoDTO> items) {
		this.items = items;
	}

	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
