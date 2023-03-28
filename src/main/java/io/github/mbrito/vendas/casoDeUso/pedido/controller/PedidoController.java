package io.github.mbrito.vendas.casoDeUso.pedido.controller;

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
	public ResponseEntity<RequestPedidoDTO> novoPedido(@RequestBody RequestPedidoDTO pedido) throws ListIsEmptyException, ResourceNotFoundException {
		pedidoService.novoPedido(pedido);
		return ResponseEntity.ok(pedido);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Pedido> editarPedido(@Valid Pedido pedido, @PathVariable int id) throws ResourceNotFoundException {		
		Pedido novoPedido = pedidoService.editarPedidoParcial(pedido, id);
		return ResponseEntity.ok(novoPedido);
	}

	@GetMapping
	public Iterable<Pedido> Pedidos() {
		return pedidoService.obterPedidos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> PedidoId(@PathVariable int id) throws ResourceNotFoundException {
		return ResponseEntity.ok(pedidoService.obterPedidoId(id));
	}
	
	@GetMapping("/cliente/{idCliente}")
	public ResponseEntity<?> PedidoIdCliente(@PathVariable int idCliente) throws ResourceNotFoundException {
		return ResponseEntity.ok(pedidoService.obterPedidoCliente(idCliente));
	}

	@GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
	public Iterable<Pedido> PedidoPagina(@PathVariable int numeroPagina, @PathVariable int qtdePagina) {
		return pedidoService.obterPedidosPorPagina(numeroPagina, qtdePagina);
	}

	@DeleteMapping(path = "/{id}")
	public void excluirPedido(@PathVariable int id) throws ResourceNotFoundException {
		pedidoService.excluirPedido(id);
	}
}
