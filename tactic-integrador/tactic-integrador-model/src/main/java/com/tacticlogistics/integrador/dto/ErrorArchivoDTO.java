package com.tacticlogistics.integrador.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
public class ErrorArchivoDTO {
	private int numeroLinea;

	@NonNull
	private String linea;

	@NonNull
	private List<String> errores;
}
