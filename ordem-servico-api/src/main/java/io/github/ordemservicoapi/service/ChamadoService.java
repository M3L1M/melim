package io.github.ordemservicoapi.service;

import java.util.List;

import io.github.ordemservicoapi.model.entity.Chamado;

public interface ChamadoService {
	List<Chamado> list();
	Chamado save(Chamado chamado);
	void autentica(Chamado chamado);
	void update(Integer id,Chamado chamado);
	void delete(Integer id);
	
	Chamado obterChamadoPorId(Integer id);
}
