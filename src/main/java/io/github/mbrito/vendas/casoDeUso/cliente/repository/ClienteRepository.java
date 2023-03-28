package io.github.mbrito.vendas.casoDeUso.cliente.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.mbrito.vendas.casoDeUso.cliente.entitie.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	@Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:parteNome%")
	public Page<Cliente> findByNomeContainingPageable(String parteNome, Pageable pageble);
	
	public Optional<Cliente> findById(Integer id);
}
