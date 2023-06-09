package io.github.mbrito.vendas.casoDeUso.pedido.entitie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.mbrito.vendas.casoDeUso.produto.entitie.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties("pedido")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Pedido pedido;
	
	@ManyToOne
	private Produto produto;
	
	private Integer quantidade;
	
	public ItemPedido() {}
	
	public ItemPedido(Integer id, Pedido pedido, Produto produto, Integer quantidade) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
