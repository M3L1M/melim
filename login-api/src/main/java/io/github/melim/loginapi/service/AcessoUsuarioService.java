package io.github.melim.loginapi.service;

import io.github.melim.loginapi.model.entity.AcessoUsuario;

public interface AcessoUsuarioService {
	AcessoUsuario criarConta(AcessoUsuario user);
	AcessoUsuario login(String username,String password);
	void autenticarConta(AcessoUsuario user);
	
	void editarConta(Integer id,AcessoUsuario user);
	
	void inativarConta(Integer id);
	void ativarConta(Integer id);
	
	AcessoUsuario obterPorId(Integer id);
}
