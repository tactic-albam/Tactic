package com.tacticlogistics.integrador.etl.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(catalog = "etl", name = "tipos_archivo")
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class TipoArchivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_archivo")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_grupo_integracion")
	@NotNull
	private GrupoIntegracion grupoInteracion;
	
	@Column(length = 50, nullable = false)
	@NotNull
	private String codigo;
	
	@Column(length = 100, nullable = false)
	@NotNull
	private String nombre;
	
	@Column(length = 200, nullable = false)
	@NotNull
	private String descripcion;
	
	@Column(length = 4, nullable = false)
	@NotNull
	private String separadorRegistros;

	@Column(length = 4, nullable = false)
	@NotNull
	private String separadorCampos;
	
	private int ordinal;
	private boolean activo; 

	@Column(nullable = false)
	@NotNull
    private LocalDateTime fechaActualizacion; 
	
	@Column(length = 50, nullable = false)
	@NotNull
    private String usuarioActualizacion;
}
