package io.github.mbrito.vendas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.mbrito.vendas.dto.input.PedidoDTO;
import io.github.mbrito.vendas.entities.Cliente;
import io.github.mbrito.vendas.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByCliente(Cliente Cliente);

	void save(PedidoDTO pedido);
	
	@Query("select p from Pedido p left join fetch p.itensPedido where p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
