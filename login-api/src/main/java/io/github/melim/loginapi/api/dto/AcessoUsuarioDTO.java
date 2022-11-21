package io.github.melim.loginapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcessoUsuarioDTO {
	private Integer id;
	private String nomeUsuario;
	private String senha;
	private String dataCadastro;
	private String status;
}