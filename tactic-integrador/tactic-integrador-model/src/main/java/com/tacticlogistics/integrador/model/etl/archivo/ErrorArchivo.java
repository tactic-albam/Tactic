package com.tacticlogistics.integrador.model.etl.archivo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NonNull;
import lombok.Value;

@Entity
@Table(catalog = "etl", name = "archivos_errores")
@Value
public class ErrorArchivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_archivo_error")
	private Long id;

	private int numeroLinea;

	@Column(nullable = false)
	@NonNull
	private String linea;

	@Column(nullable = false)
	@NonNull
	private String error;

	public ErrorArchivo() {
		super();
		this.id = null;
		this.numeroLinea = 0;
		this.linea = "";
		this.error = "";
	}
	
	public ErrorArchivo(int numeroLinea, String linea, String error) {
		super();
		this.id = null;
		this.numeroLinea = numeroLinea;
		this.linea = linea;
		this.error = error;
	}
}
