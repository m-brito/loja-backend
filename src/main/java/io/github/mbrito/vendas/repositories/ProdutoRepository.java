package io.github.mbrito.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mbrito.vendas.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
