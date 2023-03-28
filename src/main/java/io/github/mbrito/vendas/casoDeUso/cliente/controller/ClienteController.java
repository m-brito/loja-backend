package io.github.mbrito.vendas.casoDeUso.cliente.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.mbrito.vendas.casoDeUso.cliente.entitie.Cliente;
import io.github.mbrito.vendas.casoDeUso.cliente.service.ClienteService;
import io.github.mbrito.vendas.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

//	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	
	@PostMapping
	public ResponseEntity<Cliente> novoCliente(@Valid Cliente cliente) {
		clienteService.novoCliente(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Cliente> editarCliente(@Valid Cliente cliente, @PathVariable int id) throws ResourceNotFoundException {		
		Cliente novoCliente = clienteService.editarClienteParcial(cliente, id);
		return ResponseEntity.ok(novoCliente);
	}

	@GetMapping
	public Iterable<Cliente> clientes() {
		return clienteService.obterClientes();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> clienteId(@PathVariable int id) throws ResourceNotFoundException {
		return ResponseEntity.ok(clienteService.obterClienteId(id));
	}

	@GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
	public Iterable<Cliente> clientePagina(@PathVariable int numeroPagina, @PathVariable int qtdePagina) {
		return clienteService.obterClientesPorPagina(numeroPagina, qtdePagina);
	}

	@GetMapping(path = "/nome/{parteNome}")
	public Iterable<Cliente> clientesPorNomePage(@PathVariable String parteNome,
			@RequestParam(name = "pagina", defaultValue = "0") int pagina,
			@RequestParam(name = "maxPage", defaultValue = "5") int maxPage) {
		if (maxPage >= 5)
			maxPage = 5;
		return clienteService.obterClientesPorNomePage(parteNome, pagina, maxPage);
	}

	@DeleteMapping(path = "/{id}")
	public void excluirCliente(@PathVariable int id) throws ResourceNotFoundException {
		clienteService.excluirCliente(id);
	}

}
