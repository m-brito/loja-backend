package io.github.mbrito.vendas.casoDeUso.produto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.mbrito.vendas.casoDeUso.produto.entitie.Produto;
import io.github.mbrito.vendas.casoDeUso.produto.service.ProdutoService;
import io.github.mbrito.vendas.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
//	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
//	public @ResponseBody Produto novoProduto(@Valid Produto produto) {
//		produtoService.novoProduto(produto);
//		return produto;
//	}
	
	@PostMapping
	public @ResponseBody Produto novoProduto(@Valid Produto produto) {
		produtoService.novoProduto(produto);
		return produto;
	}
	
	@PatchMapping("/{id}")
	public @ResponseBody Produto editarProduto(@Valid Produto produto, @PathVariable int id) throws ResourceNotFoundException {
		Produto novoProduto = produtoService.editarProdutoParcial(produto, id);
		return novoProduto;
	}
	
	@GetMapping
	public Iterable<Produto> obterProdutos() {
		return produtoService.obterProdutos();
	}
	
	@GetMapping("/{id}")
	public Optional<Produto> obterProdutosId(@PathVariable Integer id) throws ResourceNotFoundException {
		return produtoService.obterProdutosId(id);
	}
	
	@GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
	public Iterable<Produto> obterProdutosPorPagina(@PathVariable int numeroPagina, @PathVariable int qtdePagina) {
		return produtoService.obterProdutosPorPagina(numeroPagina, qtdePagina);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluirProduto(@PathVariable int id) throws ResourceNotFoundException {
		produtoService.excluirProduto(id);
	}
}
