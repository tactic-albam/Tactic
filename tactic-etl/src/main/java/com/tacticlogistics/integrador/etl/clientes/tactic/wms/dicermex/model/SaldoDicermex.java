package com.tacticlogistics.integrador.etl.clientes.tactic.wms.dicermex.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.tacticlogistics.integrador.etl.model.oms.EtlEntidadBase;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(catalog = "dicermex", name = "saldos")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SaldoDicermex extends EtlEntidadBase {

	private static final long serialVersionUID = 1L;

	public static final String UBICACION = "UBICACION";
	public static final String PRODUCTO_EAN = "PRODUCTO_EAN";
	public static final String PRODUCTO_CODIGO = "PRODUCTO_CODIGO";
	public static final String ESTADO_INVENTARIO = "ESTADO_INVENTARIO";
	public static final String LOTE = "LOTE";
	public static final String FECHA_VENCIMIENTO = "FECHA_VENCIMIENTO";
	public static final String UNIDAD_CODIGO = "UNIDAD_CODIGO";
	public static final String CANTIDAD = "CANTIDAD";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_saldo")
	private Long id;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String ubicacion;

	@Column(length = 50, nullable = false)
	@NotNull
	private String productoEan;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String productoCodigo;

	@Column(length = 50, nullable = false)
	@NotEmpty
	private String estadoInventario;

	@Column(length = 30, nullable = false)
	@NotNull
	private String lote;

	private LocalDate fechaVencimiento;

	@Column(length = 50, nullable = false)
	@NotNull
	private String unidadCodigo;

	private int cantidad;
	
	@Builder
	public SaldoDicermex(Long id, String ubicacion, String productoEan, String productoCodigo, String estadoInventario,
			String lote, LocalDate fechaVencimiento, String unidadCodigo, Integer cantidad
			, Long idArchivo, String linea, LocalDateTime fechaCreacion,
			String usuarioCreacion) {
		super(idArchivo, linea, fechaCreacion, usuarioCreacion);
		this.id = id;
		this.ubicacion = ubicacion;
		this.productoEan = productoEan;
		this.productoCodigo = productoCodigo;
		this.estadoInventario = estadoInventario;
		this.lote = lote;
		this.fechaVencimiento = fechaVencimiento;
		this.unidadCodigo = unidadCodigo;
		this.cantidad = cantidad;
	}
	
	
}
