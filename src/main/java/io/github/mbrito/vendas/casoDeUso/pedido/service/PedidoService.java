package io.github.mbrito.vendas.casoDeUso.pedido.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.mbrito.vendas.casoDeUso.pedido.dto.RequestItemPedidoDTO;
import io.github.mbrito.vendas.casoDeUso.pedido.dto.RequestPedidoDTO;
import io.github.mbrito.vendas.casoDeUso.pedido.entitie.ItemPedido;
import io.github.mbrito.vendas.casoDeUso.pedido.entitie.Pedido;
import io.github.mbrito.vendas.casoDeUso.pedido.repository.ItemPedidoRepository;
import io.github.mbrito.vendas.casoDeUso.pedido.repository.PedidoRepository;
import io.github.mbrito.vendas.casoDeUso.produto.entitie.Produto;
import io.github.mbrito.vendas.casoDeUso.produto.repository.ProdutoRepository;
import io.github.mbrito.vendas.casoDeUso.usuario.entitie.Cliente;
import io.github.mbrito.vendas.casoDeUso.usuario.repository.ClienteRepository;
import io.github.mbrito.vendas.exceptions.ListIsEmptyException;
import io.github.mbrito.vendas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ItemPedidoRepository itemsPedidoRepository;
	@Autowired
	private ProdutoRepository produtosRepository;

	@Transactional
	public ResponseEntity<Pedido> novoPedido(RequestPedidoDTO dto) throws ListIsEmptyException, ResourceNotFoundException {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "Id", idCliente.toString()));

		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);

		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		pedidoRepository.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setItensPedido(itemsPedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
	}

	private List<ItemPedido> converterItems(Pedido pedido, List<RequestItemPedidoDTO> items) throws ListIsEmptyException, ResourceNotFoundException {
		if (items.isEmpty()) {
			throw new ListIsEmptyException("items");
		}
		
		List<ItemPedido> itemsPedido = new ArrayList<>();
		
		for(int x=0; x<items.size(); x++) {
			Integer idProduto = items.get(x).getProduto();
			Produto produto = produtosRepository.findById(idProduto)
					.orElseThrow(() -> new ResourceNotFoundException("Produto"));
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(items.get(x).getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			itemsPedido.add(itemPedido);
		}
		
		return itemsPedido;
	}

	public ResponseEntity<Pedido> editarPedido(Pedido pedido, Integer id) throws ResourceNotFoundException {
		obterPedidoId(id);
		pedidoRepository.save(pedido);
		return ResponseEntity.status(HttpStatus.OK).body(pedido);
	}

	public ResponseEntity<Pedido> editarPedidoParcial(Pedido novoPedido, Integer id) throws ResourceNotFoundException {
		ResponseEntity<Pedido> oldPedido = obterPedidoId(id);
		Pedido p = oldPedido.getBody();
		p.setDataPedido(novoPedido.getDataPedido() != null ? novoPedido.getDataPedido() : p.getDataPedido());
		pedidoRepository.save(p);
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	
	public ResponseEntity<List<Pedido>> obterPedidos() {
		return ResponseEntity.status(HttpStatus.OK).body(pedidoRepository.findAll());
	}

	public ResponseEntity<Pedido> obterPedidoId(Integer id) throws ResourceNotFoundException {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
		} else {
			throw new ResourceNotFoundException("Pedido", "Id", id.toString());
		}
	}
	
	public ResponseEntity<List<Pedido>> obterPedidoCliente(Integer idCliente) throws ResourceNotFoundException {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		if(!cliente.isPresent())
			throw new ResourceNotFoundException("Cliente", "Id", idCliente.toString());
		List<Pedido> pedidos = pedidoRepository.findByCliente(cliente);
		return ResponseEntity.status(HttpStatus.OK).body(pedidos);
	}

	public ResponseEntity<Iterable<Pedido>> obterPedidosPorPagina(int numeroPagina, int qtdePagina) {
		if (qtdePagina >= 5)
			qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return ResponseEntity.status(HttpStatus.OK).body(pedidoRepository.findAll(page));
	}
	
	@Transactional
	public ResponseEntity<Void> excluirPedido(int id) throws ResourceNotFoundException {
		ResponseEntity<Pedido> pedido = obterPedidoId(id);
		pedido.getBody().getItensPedido().stream().forEach(i -> {
			itemsPedidoRepository.deleteById(i.getId());
		});
		pedidoRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
