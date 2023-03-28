package io.github.mbrito.vendas.casoDeUso.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mbrito.vendas.casoDeUso.pedido.entitie.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
