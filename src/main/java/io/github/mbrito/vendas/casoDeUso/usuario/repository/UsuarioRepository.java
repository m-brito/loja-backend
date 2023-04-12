package io.github.mbrito.vendas.casoDeUso.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mbrito.vendas.casoDeUso.usuario.entitie.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByLogin(String login);
}
