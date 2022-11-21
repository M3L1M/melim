package io.github.ordemservicoapi.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoDTO {
	private Integer id;
	private Integer usuario;
	private LocalTime hora;
	private LocalDate data;
	private String descricao;
	private String status;
	private String situacao;
	private LocalDate dataPrevista;
	private Integer cliente;
	private BigDecimal preco;
}
