package com.tacticlogistics.integrador.model.tms.rutas.moviles;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.etl.registro.Registro;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(catalog = "tms", name = "moviles_rutacontrol")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MovilRutaControl extends Registro {

	private static final long serialVersionUID = 1L;

	public static final String MOVIL = "MOVIL";
	public static final String PLACA = "PLACA";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movil_rutacontrol")
	private Long id;

	@Column(length = 20, nullable = false)
	@NotNull
	private String movil;

	@Column(length = 20, nullable = false)
	@NotNull
	private String placa;

	@Builder
	public MovilRutaControl(Long id, String movil, String placa, 
			Long idArchivo, EstadoRegistroType estadoRegistro, int numeroLinea, String linea, 
			LocalDateTime fechaCreacion, String usuarioCreacion, 
			LocalDateTime fechaActualizacion, String usuarioActualizacion) {
		super(idArchivo, estadoRegistro, numeroLinea, linea, fechaCreacion, usuarioCreacion, fechaActualizacion, usuarioActualizacion);
		this.id = id;
		this.movil = movil;
		this.placa = placa;
	}

	
}
