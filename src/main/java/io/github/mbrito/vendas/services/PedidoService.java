package io.github.mbrito.vendas.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.mbrito.vendas.dto.ItemPedidoDTO;
import io.github.mbrito.vendas.dto.PedidoDTO;
import io.github.mbrito.vendas.entities.Cliente;
import io.github.mbrito.vendas.entities.ItemPedido;
import io.github.mbrito.vendas.entities.Pedido;
import io.github.mbrito.vendas.entities.Produto;
import io.github.mbrito.vendas.exceptions.ListIsEmptyException;
import io.github.mbrito.vendas.exceptions.ResourceNotFoundException;
import io.github.mbrito.vendas.repositories.ClienteRepository;
import io.github.mbrito.vendas.repositories.ItemPedidoRepository;
import io.github.mbrito.vendas.repositories.PedidoRepository;
import io.github.mbrito.vendas.repositories.ProdutoRepository;
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
	public Pedido novoPedido(PedidoDTO dto) throws Exception {
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
		return pedido;
	}

	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) throws ListIsEmptyException {
		if (items.isEmpty()) {
			throw new ListIsEmptyException("items");
		}

		return items.stream().map(dto -> {
			Integer idProduto = dto.getProduto();
			Optional<Produto> produto = produtosRepository.findById(idProduto);
//			if(!produto.isPresent())
//				throw new ResourceNotFoundException("Produto", "Id", idProduto.toString());

			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto.get());
			return itemPedido;
		}).collect(Collectors.toList());

	}

	public Pedido editarPedido(Pedido pedido, Integer id) throws ResourceNotFoundException {
		obterPedidoId(id);
		pedidoRepository.save(pedido);
		return pedido;
	}

	public Pedido editarPedidoParcial(Pedido novoPedido, Integer id) throws ResourceNotFoundException {
		Optional<Pedido> oldPedido = obterPedidoId(id);
		Pedido p = oldPedido.get();
		p.setDataPedido(novoPedido.getDataPedido() != null ? novoPedido.getDataPedido() : p.getDataPedido());
		pedidoRepository.save(p);
		return p;
	}
	
	public Iterable<Pedido> obterPedidos() {
		System.out.println(pedidoRepository.findAll());
		return pedidoRepository.findAll();
	}

	public Optional<Pedido> obterPedidoId(Integer id) throws ResourceNotFoundException {
		Optional<Pedido> Pedido = pedidoRepository.findById(id);
		if (Pedido.isPresent()) {
			return Pedido;
		} else {
			throw new ResourceNotFoundException("Pedido", "Id", id.toString());
		}
	}

	public Iterable<Pedido> obterPedidosPorPagina(int numeroPagina, int qtdePagina) {
		if (qtdePagina >= 5)
			qtdePagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdePagina);
		return pedidoRepository.findAll(page);
	}

	public void excluirPedido(int id) throws ResourceNotFoundException {
		obterPedidoId(id);
		pedidoRepository.deleteById(id);
	}
}
