package io.github.melim.loginapi.service.impl;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.melim.loginapi.exception.ErroCadastroException;
import io.github.melim.loginapi.exception.ErroRegraNegocioException;
import io.github.melim.loginapi.model.entity.AcessoUsuario;
import io.github.melim.loginapi.model.enums.Status;
import io.github.melim.loginapi.model.repository.AcessoUsuarioRepository;
import io.github.melim.loginapi.service.AcessoUsuarioService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AcessoUsuarioServiceImpl implements AcessoUsuarioService{
	
	@Autowired
	private AcessoUsuarioRepository repository;
	
	@Override
	@Transactional
	public AcessoUsuario criarConta(AcessoUsuario user) {
		
		user.setStatus(Status.ACTIVE);
		user.setDataCadastro(LocalDate.now());
		autenticarConta(user);
		return repository.save(user);
	}

	@Override
	public AcessoUsuario login(String username, String password) {
		AcessoUsuario userAccess=repository
				.findByNomeUsuario(username)
				.orElseThrow(()-> new ResponseStatusException(NOT_FOUND,"Usuario não cadastrado"));
		if(!password.equalsIgnoreCase(userAccess.getSenha())) {
			throw new ErroRegraNegocioException("Senha invalida");
		}
		
		if(userAccess.getStatus()==Status.INACTIVE) {
			throw new ErroRegraNegocioException("Conta inativa");
		}
		
		
		return userAccess;
	}

	@Override
	public void autenticarConta(AcessoUsuario user) {
		if(user.getNomeUsuario().isEmpty()) {
			throw new ErroCadastroException("Usuario não registrado.");
		}
		
		if(user.getSenha().isEmpty()) {
			throw new ErroCadastroException("Senha não registrada.");
		}
		
		if(user.getSenha().length()<10) {
			throw new ErroCadastroException("As senhas devem ter pelo menos 10 caracteres.");
		}
		
		boolean existUsername=repository.existsByNomeUsuario(user.getSenha());
		
		if(existUsername) {
			throw new ErroCadastroException("Este usuario ja existe.");
		}
		
		
	}

	@Override
	@Transactional
	public void editarConta(Integer id, AcessoUsuario user) {
		repository
		.findById(id)
		.map(entity->{
			entity.setNomeUsuario(user.getNomeUsuario());
			entity.setSenha(user.getSenha());
			return entity;
		}).orElseThrow(()-> new ErroRegraNegocioException("Conta não encontrada."));

		
	}

	@Override
	@Transactional
	public void inativarConta(Integer id) {
		repository
		.findById(id)
		.map(entity->{
			entity.setStatus(Status.INACTIVE);
			return entity;
		}).orElseThrow(()-> new ErroRegraNegocioException("Conta não encontrada."));
	}

	@Override
	@Transactional
	public void ativarConta(Integer id) {
		repository
		.findById(id)
		.map(entity->{
			entity.setStatus(Status.ACTIVE);
			return entity;
		}).orElseThrow(()-> new ErroRegraNegocioException("Conta não encontrada."));
		
	}

	@Override
	public AcessoUsuario obterPorId(Integer id) {
		return repository
				.findById(id)
				.orElseThrow(()->new ErroRegraNegocioException("Conta não encontrada."));
	}

}
