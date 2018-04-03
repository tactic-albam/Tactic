package com.tacticlogistics.integrador.files.clientes.tactic.wms.pf;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.wms.pf.ProntoFormProductoMedida;

import lombok.val;

public class MapEntidadProntoFormProductoMedidaDecorator extends MapEntidadDecorator<ProntoFormProductoMedida> {

	public MapEntidadProntoFormProductoMedidaDecorator() {
		super();
	}

	public MapEntidadProntoFormProductoMedidaDecorator(Filter<ProntoFormProductoMedida> inner) {
		super(inner);
	}

	@Override
	protected ProntoFormProductoMedida map(ArchivoDTO<ProntoFormProductoMedida> archivoDTO, RegistroDTO<ProntoFormProductoMedida> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Validate.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Validate.notNull(archivo);
		val datos = registro.getDatos();
		Validate.notEmpty(datos);

		Integer caseLevel = getInteger(tipoArchivo, datos, ProntoFormProductoMedida.CASE_LEVEL);
		Integer factorConversion1 = getInteger(tipoArchivo, datos, ProntoFormProductoMedida.FACTOR_CONVERSION_1);
		Integer factorConversion2 = getInteger(tipoArchivo, datos, ProntoFormProductoMedida.FACTOR_CONVERSION_2);
		Integer factorConversion3 = getInteger(tipoArchivo, datos, ProntoFormProductoMedida.FACTOR_CONVERSION_3);

		BigDecimal largo1 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.LARGO_1);
		BigDecimal ancho1 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.ANCHO_1);
		BigDecimal alto1 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.ALTO_1);
		BigDecimal peso1 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.PESO_1);
		BigDecimal volumen1 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.VOLUMEN_1);

		BigDecimal largo2 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.LARGO_2);
		BigDecimal ancho2 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.ANCHO_2);
		BigDecimal alto2 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.ALTO_2);
		BigDecimal peso2 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.PESO_2);
		BigDecimal volumen2 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.VOLUMEN_2);

		BigDecimal largo3 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.LARGO_3);
		BigDecimal ancho3 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.ANCHO_3);
		BigDecimal alto3 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.ALTO_3);
		BigDecimal peso3 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.PESO_3);
		BigDecimal volumen3 = getBigDecimal(tipoArchivo, datos, ProntoFormProductoMedida.VOLUMEN_3);

		// @formatter:off
		val result = ProntoFormProductoMedida.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.numeroLinea(registro.getNumeroLinea())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())
				
				.clienteCodigo(datos.get(ProntoFormProductoMedida.CLIENTE_CODIGO))
				.productoCodigo(datos.get(ProntoFormProductoMedida.PRODUCTO_CODIGO))
				.productoNombre(datos.get(ProntoFormProductoMedida.PRODUCTO_NOMBRE))
				.bodegaCodigo(datos.get(ProntoFormProductoMedida.BODEGA_CODIGO))
				.huellaCodigo(datos.get(ProntoFormProductoMedida.HUELLA_CODIGO))
				.caseLevel(caseLevel)
				.unidadCodigo1(datos.get(ProntoFormProductoMedida.UNIDAD_CODIGO_1))
				.factorConversion1(factorConversion1)
				.largo1(largo1)
				.ancho1(ancho1)
				.alto1(alto1)
				.peso1(peso1)
				.volumen1(volumen1)
				.unidadCodigo2(datos.get(ProntoFormProductoMedida.UNIDAD_CODIGO_2))
				.factorConversion2(factorConversion2)
				.largo2(largo2)
				.ancho2(ancho2)
				.alto2(alto2)
				.peso2(peso2)
				.volumen2(volumen2)
				.unidadCodigo3(datos.get(ProntoFormProductoMedida.UNIDAD_CODIGO_3))
				.factorConversion3(factorConversion3)
				.largo3(largo3)
				.ancho3(ancho3)
				.alto3(alto3)
				.peso3(peso3)
				.volumen3(volumen3)
				.build();
		// @formatter:on

		return result;
	}
}