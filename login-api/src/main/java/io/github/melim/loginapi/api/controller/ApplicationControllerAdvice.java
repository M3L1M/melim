package io.github.melim.loginapi.api.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

import io.github.melim.loginapi.api.ApiErrors;
import io.github.melim.loginapi.exception.ErroRegraNegocioException;
import io.github.melim.loginapi.exception.ErroCadastroException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	
	@ExceptionHandler(ErroCadastroException.class)
	@ResponseStatus(BAD_REQUEST)
	public ApiErrors handleErrorRegistrationException(ErroCadastroException exception) {
		String message=exception.getMessage();
		return new ApiErrors(message);
	}
	
	@ExceptionHandler(ErroRegraNegocioException.class)
	@ResponseStatus(BAD_REQUEST)
	public ApiErrors handleErroRegraNegocio(ErroRegraNegocioException exception) {
		String message=exception.getMessage();
		return new ApiErrors(message);
	}
	
	
}
