package io.github.ordemservicoapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.ordemservicoapi.model.entity.Chamado;
import io.github.ordemservicoapi.model.repository.ChamadoRepository;
import io.github.ordemservicoapi.service.ChamadoService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class ChamadoServiceImpl implements ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
	@Override
	public List<Chamado> list() {
		return repository.findAll();
	}

	@Override
	public Chamado save(Chamado chamado) {
		autentica(chamado); 
		return repository.save(chamado);
	}

	@Override
	public Chamado update(Integer id, Chamado chamado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chamado delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chamado obterChamadoPorId(Integer id) {
		
		return repository.findById(id).orElseThrow(()-> new ResponseStatusException(NOT_FOUND,"Chamado não encontrado"));
	}

	@Override
	public void autentica(Chamado chamado) {
		
	}

}
