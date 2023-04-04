package io.github.mbrito.vendas.casoDeUso.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.mbrito.vendas.casoDeUso.pedido.dto.RequestPedidoDTO;
import io.github.mbrito.vendas.casoDeUso.pedido.entitie.Pedido;
import io.github.mbrito.vendas.casoDeUso.pedido.service.PedidoService;
import io.github.mbrito.vendas.exceptions.ListIsEmptyException;
import io.github.mbrito.vendas.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;

//	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	
	@PostMapping
	public ResponseEntity<Pedido> novoPedido(@RequestBody @Valid RequestPedidoDTO pedido) throws ListIsEmptyException, ResourceNotFoundException {
		return pedidoService.novoPedido(pedido);
	}
	
	@PatchMapping(path = "/{id}")
	public ResponseEntity<Pedido> editarPedido(@Valid Pedido pedido, @PathVariable int id) throws ResourceNotFoundException {		
		return pedidoService.editarPedidoParcial(pedido, id);
	}

	@GetMapping
	public ResponseEntity<List<Pedido>> Pedidos() {
		return pedidoService.obterPedidos();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Pedido> PedidoId(@PathVariable int id) throws ResourceNotFoundException {
		return pedidoService.obterPedidoId(id);
	}
	
	@GetMapping(path = "/cliente/{idCliente}")
	public ResponseEntity<List<Pedido>> PedidoIdCliente(@PathVariable int idCliente) throws ResourceNotFoundException {
		return pedidoService.obterPedidoCliente(idCliente);
	}

	@GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
	public ResponseEntity<Iterable<Pedido>> PedidoPagina(@PathVariable int numeroPagina, @PathVariable int qtdePagina) {
		return pedidoService.obterPedidosPorPagina(numeroPagina, qtdePagina);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> excluirPedido(@PathVariable int id) throws ResourceNotFoundException {
		return pedidoService.excluirPedido(id);
	}
}
