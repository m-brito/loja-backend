package io.github.mbrito.vendas.casoDeUso.cliente.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.mbrito.vendas.casoDeUso.cliente.entitie.Cliente;
import io.github.mbrito.vendas.casoDeUso.cliente.repository.ClienteRepository;
import io.github.mbrito.vendas.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente novoCliente(Cliente cliente) {
		clienteRepository.save(cliente);
		return cliente;
	}
	
	public Cliente editarCliente(Cliente cliente, Integer id) throws ResourceNotFoundException {
		obterClienteId(id);
		clienteRepository.save(cliente);
		return cliente;
	}
	
	public Cliente editarClienteParcial(Cliente novoCliente, Integer id) throws ResourceNotFoundException {
		Optional<Cliente> oldCliente = obterClienteId(id);
		Cliente c = oldCliente.get();
		c.setNome(novoCliente.getNome() != null ? novoCliente.getNome() : c.getNome());
		clienteRepository.save(c);
		return c;
	}
	
	public Iterable<Cliente> obterClientes() {
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> obterClienteId(Integer id) throws ResourceNotFoundException {
		Optional<Cliente> clientes = clienteRepository.findById(id); 
		if(clientes.isPresent()) {
			return clientes;
		} else {
			throw new ResourceNotFoundException("Cliente", "Id", id.toString());
		}
	}
	
	public Iterable<Cliente> obterClientesPorPagina(int numeroPagina, int qtdePagina) {
		if(qtdePagina >=5) qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return clienteRepository.findAll(page);
	}
	
	public Iterable<Cliente> obterClientesPorNomePage(String parteNome, int pagina, int maxPage) {
		if(maxPage >= 5) maxPage = 5;
		return clienteRepository.findByNomeContainingPageable(parteNome, PageRequest.of(pagina, maxPage));
	}
	
	public void excluirCliente(int id) throws ResourceNotFoundException {
		obterClienteId(id);
		clienteRepository.deleteById(id);
	}

}
