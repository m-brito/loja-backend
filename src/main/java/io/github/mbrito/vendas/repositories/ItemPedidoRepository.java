package io.github.mbrito.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mbrito.vendas.entities.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
