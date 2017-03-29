package com.tacticlogistics.integrador.etl.handlers.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class RegistroDTO<T> {
	@Setter
	private T entidad;
	@NonNull
	private String linea;
	
	private Map<String, String> datos;
	private Map<String, String> originales;
	private List<String> errores;

	public static class RegistroDTOBuilder<T> {
		private Map<String, String> datos = new HashMap<>();
		private Map<String, String> originales = new HashMap<>();
		private List<String> errores = new ArrayList<>();
	}

	public RegistroDTO(RegistroDTO<T> dto) {
		super();
		this.entidad = dto.getEntidad();
		this.linea = dto.getLinea();
		this.datos = new HashMap<>();
		this.originales = new HashMap<>();
		this.errores = new ArrayList<>();
		this.datos.putAll(dto.getDatos());
		this.originales.putAll(dto.getOriginales());
		this.errores.addAll(dto.getErrores());
	}
}
