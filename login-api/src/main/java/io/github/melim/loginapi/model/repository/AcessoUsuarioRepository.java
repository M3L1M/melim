package io.github.melim.loginapi.model.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.melim.loginapi.model.entity.AcessoUsuario;

public interface AcessoUsuarioRepository extends JpaRepository<AcessoUsuario, Integer>{

	boolean existsByNomeUsuario(String nomeUsuario);

	Optional<AcessoUsuario> findByNomeUsuario(String nomeUsuario);
	
}
