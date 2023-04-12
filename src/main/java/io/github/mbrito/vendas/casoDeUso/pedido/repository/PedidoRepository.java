package io.github.mbrito.vendas.casoDeUso.pedido.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.mbrito.vendas.casoDeUso.pedido.dto.RequestPedidoDTO;
import io.github.mbrito.vendas.casoDeUso.pedido.entitie.Pedido;
import io.github.mbrito.vendas.casoDeUso.usuario.entitie.Cliente;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByCliente(Optional<Cliente> cliente);

	void save(RequestPedidoDTO pedido);
	
	@Query("select p from Pedido p left join fetch p.itensPedido where p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
