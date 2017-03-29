package com.tacticlogistics.integrador.etl.model.etl;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class TipoArchivoConfiguracion{
	@Column(nullable = false, length = 50)
	@NotNull
	private String codigo;

	@Column(nullable = false, length = 300)
	@NotNull
	private String valor;
}