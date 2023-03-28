package io.github.mbrito.vendas.casoDeUso.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mbrito.vendas.casoDeUso.produto.entitie.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
