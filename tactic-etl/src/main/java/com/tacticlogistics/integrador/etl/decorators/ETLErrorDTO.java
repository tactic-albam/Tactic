package com.tacticlogistics.integrador.etl.decorators;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
public class ETLErrorDTO {
	private int numeroLinea;

	@NonNull
	private String linea;

	@NonNull
	private List<String> errores;
}
