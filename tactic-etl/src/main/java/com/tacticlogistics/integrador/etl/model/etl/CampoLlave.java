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
public class CampoLlave{
	@Column(nullable = false, length = 50)
	@NotNull
	private String llave;

	private long idCampo;
}