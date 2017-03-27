package com.tacticlogistics.integrador.etl.handlers.tactic.wms.dicermex;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.decorators.Filter;
import com.tacticlogistics.integrador.etl.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.handlers.dicermex.model.Salida;
import com.tacticlogistics.integrador.etl.handlers.tactic.wms.dicermex.model.SaldoDicermex;

import lombok.val;

public class MapEntidadSaldoDicermexDecorator extends MapEntidadDecorator<SaldoDicermex> {

	public MapEntidadSaldoDicermexDecorator() {
		super();
	}

	public MapEntidadSaldoDicermexDecorator(Filter<SaldoDicermex> inner) {
		super(inner);
	}

	@Override
	protected SaldoDicermex map(ArchivoDTO<SaldoDicermex> archivoDTO, RegistroDTO<SaldoDicermex> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		val fechaVencimiento = getLocalDate(tipoArchivo, datos, SaldoDicermex.FECHA_VENCIMIENTO);
		Integer cantidad = getInteger(tipoArchivo, datos, SaldoDicermex.CANTIDAD);

		// @formatter:off
		val result = 
				SaldoDicermex.builder()
				.idArchivo(archivo.getId())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.ubicacion(datos.get(SaldoDicermex.UBICACION))
				.productoEan(datos.get(SaldoDicermex.PRODUCTO_EAN))
				.productoCodigo(datos.get(SaldoDicermex.PRODUCTO_CODIGO))
				.estadoInventario(datos.get(SaldoDicermex.ESTADO_INVENTARIO))
				.lote(datos.get(SaldoDicermex.LOTE))
				.fechaVencimiento(fechaVencimiento)
				.unidadCodigo(datos.get(SaldoDicermex.UNIDAD_CODIGO))
				.cantidad(cantidad)
				.build();
		// @formatter:on

		return result;
	}
}