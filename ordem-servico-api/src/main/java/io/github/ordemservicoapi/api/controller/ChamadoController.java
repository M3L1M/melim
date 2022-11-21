package io.github.ordemservicoapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.ordemservicoapi.api.dto.ChamadoDTO;
import io.github.ordemservicoapi.model.entity.Chamado;
import io.github.ordemservicoapi.model.enums.Situacao;
import io.github.ordemservicoapi.model.enums.Status;
import io.github.ordemservicoapi.service.ChamadoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/chamado")
public class ChamadoController {
	
	@Autowired
	private ChamadoService service;
	
	
	
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Chamado save(@RequestBody ChamadoDTO dto) {
		Chamado chamado=converteParaChamado(dto);
		return service.save(chamado);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void update(@PathVariable("id") Integer id,@RequestBody ChamadoDTO dto) {
		Chamado chamado=converteParaChamado(dto);
		service.update(id, chamado);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id);
	}

	private Chamado converteParaChamado(ChamadoDTO dto) {
		Chamado chamado=new Chamado();
		chamado.setUsuario(dto.getUsuario());
		chamado.setDescricao(dto.getDescricao());
		chamado.setStatus(Status.valueOf(dto.getStatus()));
		chamado.setSituacao(Situacao.valueOf(dto.getSituacao()));
		chamado.setPreco(dto.getPreco());
		return chamado;
	}
	
}
