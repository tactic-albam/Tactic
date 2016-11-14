package com.tacticlogistics.ext.dicermex.aud.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalleDTO {
	private int id;
	private String numeroOrden;
	private int a;
	private int b;
	private int c;
	private int d;
	private int e;
	private int f;
	private int g;
	private int h;
	private int i;
	private int j;
	private LocalDate fechaArchivo;
	private String sucursal;
	private String tipoDocumento;
	private String canal;
	private String destinatarioIdentificacion;
	private String destinatarioNombre;
	private LocalDate fechaMinima;
	private LocalDate fechaMaxima;
	private LocalTime horaMinima;
	private LocalTime horaMaxima;
	private String notas;
	private String ciudad;
	private String destinoDireccion;
	private String destinoNombre;
}
