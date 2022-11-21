package io.github.ordemservicoapi.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.ordemservicoapi.exception.ErroRegraNegocioException;
import io.github.ordemservicoapi.model.entity.Chamado;
import io.github.ordemservicoapi.model.repository.ChamadoRepository;
import io.github.ordemservicoapi.service.ChamadoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Service
public class ChamadoServiceImpl implements ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
	@Override
	public List<Chamado> list() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public Chamado save(Chamado chamado) {
		autentica(chamado); 
		chamado.setData(LocalDate.now());
		chamado.setHora(LocalTime.now());
		
		return repository.save(chamado);
	}

	@Override
	@Transactional
	public void update(Integer id, Chamado chamado) {
		repository
			.findById(id)
			.map(entity -> {
				entity.setDescricao(chamado.getDescricao());
				entity.setStatus(chamado.getStatus());
				entity.setSituacao(chamado.getSituacao());
				entity.setDataPrevista(chamado.getDataPrevista());
				entity.setPreco(chamado.getPreco());
				return entity;
			}).orElseThrow(()-> new ErroRegraNegocioException("Ordem de Serviço não encontrado"));
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Chamado chamado=repository
				.findById(id).orElseThrow(()->new ErroRegraNegocioException("Ordem de Serviço não encontrado"));
		repository.delete(chamado);
	}

	@Override
	public Chamado obterChamadoPorId(Integer id) {
		
		return repository.findById(id).orElseThrow(()-> new ResponseStatusException(NOT_FOUND,"Chamado não encontrado"));
	}

	@Override
	public void autentica(Chamado chamado) {
		
	}

}
