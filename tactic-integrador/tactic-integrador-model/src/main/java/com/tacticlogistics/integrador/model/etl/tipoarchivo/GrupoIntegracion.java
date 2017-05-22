package com.tacticlogistics.integrador.model.etl.tipoarchivo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(catalog = "etl", name = "grupos_integracion")
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class GrupoIntegracion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_grupo_integracion")
	private Long id;
	
	@Column(length = 50, nullable = false)
	@NotNull
	private String codigo;
	
	
	@Column(length = 100, nullable = false)
	@NotNull
	private String nombre;

	
	@Column(length = 200, nullable = false)
	@NotNull
	private String descripcion;
	
	private int ordinal;
    private boolean activo;
	
	@Column(nullable = false)
	@NotNull
    private LocalDateTime fechaActualizacion; 
	
	@Column(length = 50, nullable = false)
	@NotNull
    private String usuarioActualizacion;
}
