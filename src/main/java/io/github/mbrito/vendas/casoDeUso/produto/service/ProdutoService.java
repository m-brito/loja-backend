package io.github.mbrito.vendas.casoDeUso.produto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.mbrito.vendas.casoDeUso.produto.entitie.Produto;
import io.github.mbrito.vendas.casoDeUso.produto.repository.ProdutoRepository;
import io.github.mbrito.vendas.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto novoProduto(Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}

	public Produto editarProduto(Produto produto, Integer id) throws ResourceNotFoundException {
		obterProdutosId(id);
		produtoRepository.save(produto);
		return produto;
	}

	public Produto editarProdutoParcial(Produto novoProduto, Integer id) throws ResourceNotFoundException {
		Optional<Produto> oldProduto = obterProdutosId(id);
		Produto p = oldProduto.get();
		p.setDescricao(novoProduto.getDescricao() != null ? novoProduto.getDescricao() : p.getDescricao());
		p.setPreco(novoProduto.getPreco() != null ? novoProduto.getPreco() : p.getPreco());
		produtoRepository.save(p);
		return p;
	}

	public Iterable<Produto> obterProdutos() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> obterProdutosId(Integer id) throws ResourceNotFoundException {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return produto;
		} else {
			throw new ResourceNotFoundException("Produto", "Id", id.toString());
		}
	}

	public Iterable<Produto> obterProdutosPorPagina(int numeroPagina, int qtdePagina) {
		if (qtdePagina >= 5)
			qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return produtoRepository.findAll(page);
	}

	public void excluirProduto(int id) throws ResourceNotFoundException {
		obterProdutosId(id);
		produtoRepository.deleteById(id);
	}
}
