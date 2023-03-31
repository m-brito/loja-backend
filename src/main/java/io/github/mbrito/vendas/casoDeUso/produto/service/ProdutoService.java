package io.github.mbrito.vendas.casoDeUso.produto.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.mbrito.vendas.casoDeUso.produto.entitie.Produto;
import io.github.mbrito.vendas.casoDeUso.produto.repository.ProdutoRepository;
import io.github.mbrito.vendas.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public ResponseEntity<Produto> novoProduto(Produto produto) {
		Produto produtoSalvo = produtoRepository.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + produtoSalvo.getId()).build().toUri();
		return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(produto);
	}

	public ResponseEntity<Produto> editarProduto(Produto produto, Integer id) throws ResourceNotFoundException {
		obterProdutosId(id);
		produtoRepository.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(uri).body(produto);
	}

	public ResponseEntity<Produto> editarProdutoParcial(Produto novoProduto, Integer id) throws ResourceNotFoundException {
		ResponseEntity<Produto> oldProduto = obterProdutosId(id);
		Produto p = oldProduto.getBody();
		p.setDescricao(novoProduto.getDescricao() != null ? novoProduto.getDescricao() : p.getDescricao());
		p.setPreco(novoProduto.getPreco() != null ? novoProduto.getPreco() : p.getPreco());
		produtoRepository.save(p);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(uri).body(p);
	}

	public ResponseEntity<List<Produto>> obterProdutos() {
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
	}

	public ResponseEntity<Produto> obterProdutosId(Integer id) throws ResourceNotFoundException {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(produto.get());
		} else {
			throw new ResourceNotFoundException("Produto", "Id", id.toString());
		}
	}

	public ResponseEntity<Iterable<Produto>> obterProdutosPorPagina(int numeroPagina, int qtdePagina) {
		if (qtdePagina >= 5) qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll(page));
	}

	public ResponseEntity<Void> excluirProduto(int id) throws ResourceNotFoundException {
		obterProdutosId(id);
		produtoRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
