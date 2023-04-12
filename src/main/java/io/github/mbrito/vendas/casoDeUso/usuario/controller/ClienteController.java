package io.github.mbrito.vendas.casoDeUso.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import io.github.mbrito.vendas.casoDeUso.usuario.entitie.Cliente;
import io.github.mbrito.vendas.casoDeUso.usuario.service.ClienteService;
import io.github.mbrito.vendas.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

//	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	
	@PostMapping
	public ResponseEntity<Cliente> novoCliente(@RequestBody @Valid Cliente cliente) {
		return clienteService.novoCliente(cliente);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Cliente> editarCliente(@RequestBody @Valid Cliente cliente, @PathVariable int id) throws ResourceNotFoundException {		
		return clienteService.editarClienteParcial(cliente, id);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> clientes() {
		return clienteService.obterClientes();
	}
	
	@GetMapping(path = "/filtro")
	public ResponseEntity<List<Cliente>> clientesFiltro(@RequestBody(required = false) Cliente c) {
		return clienteService.obterClientesFiltro(c);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> clienteId(@RequestBody @PathVariable int id) throws ResourceNotFoundException {
		return clienteService.obterClienteId(id);
	}

	@GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
	public ResponseEntity<Iterable<Cliente>> clientePagina(@PathVariable int numeroPagina, @PathVariable int qtdePagina) {
		return clienteService.obterClientesPorPagina(numeroPagina, qtdePagina);
	}

	@GetMapping(path = "/nome/{parteNome}")
	public ResponseEntity<Iterable<Cliente>> clientesPorNomePage(@PathVariable String parteNome,
			@RequestParam(name = "pagina", defaultValue = "0") int pagina,
			@RequestParam(name = "maxPage", defaultValue = "5") int maxPage) {
		if (maxPage >= 5)
			maxPage = 5;
		return clienteService.obterClientesPorNomePage(parteNome, pagina, maxPage);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> excluirCliente(@PathVariable int id) throws ResourceNotFoundException {
		return clienteService.excluirCliente(id);
	}

}
