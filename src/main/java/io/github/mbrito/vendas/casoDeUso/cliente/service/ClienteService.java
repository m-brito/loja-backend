package io.github.mbrito.vendas.casoDeUso.cliente.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.mbrito.vendas.casoDeUso.cliente.entitie.Cliente;
import io.github.mbrito.vendas.casoDeUso.cliente.repository.ClienteRepository;
import io.github.mbrito.vendas.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public ResponseEntity<Cliente> novoCliente(Cliente cliente) {
		Cliente clienteSalvo = clienteRepository.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + clienteSalvo.getId()).build().toUri();
		return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(cliente);
	}
	
	public ResponseEntity<Cliente> editarCliente(Cliente cliente, Integer id) throws ResourceNotFoundException {
		obterClienteId(id);
		clienteRepository.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(uri).body(cliente);
	}
	
	public ResponseEntity<Cliente> editarClienteParcial(Cliente novoCliente, Integer id) throws ResourceNotFoundException {
		ResponseEntity<Cliente> oldCliente = obterClienteId(id);
		Cliente c = oldCliente.getBody();
		c.setNome(novoCliente.getNome() != null ? novoCliente.getNome() : c.getNome());
		clienteRepository.save(c);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(uri).body(c);
	}
	
	public ResponseEntity<List<Cliente>> obterClientes() {
		List<Cliente> clientes = clienteRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}
	
	public ResponseEntity<Cliente> obterClienteId(Integer id) throws ResourceNotFoundException {
		Optional<Cliente> clientes = clienteRepository.findById(id); 
		if(clientes.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(clientes.get());
		} else {
			throw new ResourceNotFoundException("Cliente", "Id", id.toString());
		}
	}
	
	public ResponseEntity<Iterable<Cliente>> obterClientesPorPagina(int numeroPagina, int qtdePagina) {
		if(qtdePagina >=5) qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll(page));
	}
	
	public ResponseEntity<Iterable<Cliente>> obterClientesPorNomePage(String parteNome, int pagina, int maxPage) {
		if(maxPage >= 5) maxPage = 5;
		return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findByNomeContainingPageable(parteNome, PageRequest.of(pagina, maxPage)));
	}
	
	public ResponseEntity<Void> excluirCliente(int id) throws ResourceNotFoundException {
		obterClienteId(id);
		clienteRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

}
