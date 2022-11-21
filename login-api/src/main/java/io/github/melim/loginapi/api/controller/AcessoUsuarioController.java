package io.github.melim.loginapi.api.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpStatus.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.melim.loginapi.api.dto.AcessoUsuarioDTO;
import io.github.melim.loginapi.model.entity.AcessoUsuario;
import io.github.melim.loginapi.model.enums.Status;
import io.github.melim.loginapi.service.AcessoUsuarioService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user-access")
public class AcessoUsuarioController {
	@Autowired
	private AcessoUsuarioService service;
	
	@PostMapping("/create-account")
	@ResponseStatus(CREATED)
	public AcessoUsuario create(@RequestBody AcessoUsuarioDTO dto) {
		AcessoUsuario create=converterAcessoUsuarioDTO(dto);
		if(dto.getDataCadastro()!=null) {
			DateTimeFormatter parser = DateTimeFormatter.ofPattern("uuuu-MM-dd");
			create.setDataCadastro(LocalDate.parse(dto.getDataCadastro(),parser));
		}
		if(dto.getStatus()!=null) {
			create.setStatus(Status.valueOf(dto.getStatus()));
		}
		return service.criarConta(create);
	}
	
	@PostMapping
	@ResponseStatus(OK)
	public AcessoUsuario login(@RequestBody AcessoUsuarioDTO dto) {
		AcessoUsuario userAccess=converterAcessoUsuarioDTO(dto);
		return service.login(userAccess.getNomeUsuario(),userAccess.getSenha());
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void update(@PathVariable("id") Integer id,@RequestBody AcessoUsuarioDTO dto) {
		AcessoUsuario userAccess=converterAcessoUsuarioDTO(dto);
		service.editarConta(id, userAccess);
	}
	
	@PutMapping("/status/{id}")
	@ResponseStatus(NO_CONTENT)
	public void updateStatus(@PathVariable("id") Integer id) {
		AcessoUsuario userAccess=service.obterPorId(id);
		
		if(userAccess.getStatus()==Status.ACTIVE) {
			service.inativarConta(id);
		}else {
			service.ativarConta(id);
		}
	}
	
	
	

	private AcessoUsuario converterAcessoUsuarioDTO(AcessoUsuarioDTO dto) {
		AcessoUsuario userAccess=new AcessoUsuario();
		userAccess.setSenha(dto.getSenha().toUpperCase());
		userAccess.setNomeUsuario(dto.getNomeUsuario().toUpperCase());
		
		return userAccess;
	}
}
