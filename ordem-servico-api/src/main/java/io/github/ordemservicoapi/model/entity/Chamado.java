package io.github.ordemservicoapi.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import io.github.ordemservicoapi.model.enums.Situacao;
import io.github.ordemservicoapi.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="chamado")
public class Chamado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="id_usuario")
	private Integer usuario;
	
	@Column(name="hora")
	@Convert(converter=Jsr310JpaConverters.LocalTimeConverter.class)
	private LocalTime hora;
	
	@Column(name="data")
	@Convert(converter=Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate data;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@Column(name="data_prevista")
	private LocalDate dataPrevista;
	
	@Column(name="id_cliente")
	private Integer cliente;
	
	@Column(name="preco",precision = 20,scale = 2)
	private BigDecimal preco;
	
}
