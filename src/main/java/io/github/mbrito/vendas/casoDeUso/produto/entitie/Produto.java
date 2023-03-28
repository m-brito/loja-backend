package io.github.mbrito.vendas.casoDeUso.produto.entitie;

import java.math.BigDecimal;
import java.util.List;

import io.github.mbrito.vendas.casoDeUso.pedido.entitie.ItemPedido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "{descricao.not.blank}")
	private String descricao;
	private BigDecimal preco;
	
	@OneToMany(mappedBy = "produto")
	private List<ItemPedido> itensPedido;
	
	public Produto() {}
	
	public Produto(Integer id, String descricao, BigDecimal preco) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", preco=" + preco + ", itensPedido=" + itensPedido
				+ "]";
	}
	
	
}
