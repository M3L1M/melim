package io.github.ordemservicoapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ordemservicoapi.model.entity.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{
	
}
