package com.tacticlogistics.integrador.etl.clientes.heinz.maestros;

import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.clientes.heinz.maestros.model.ProductoPrecio;
import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;
import com.tacticlogistics.integrador.etl.model.etl.Campo;

import lombok.val;

public class ExtraerCamposProductoPrecioDecorator extends Decorator<ProductoPrecio> {

	public ExtraerCamposProductoPrecioDecorator() {
		super();
	}

	public ExtraerCamposProductoPrecioDecorator(Filter<ProductoPrecio> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<ProductoPrecio> transformar(ArchivoDTO<ProductoPrecio> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		val registros = result.getRegistros();
		Assert.notEmpty(registros);

		val tipoArchivo = result.getTipoArchivo();
		Assert.notNull(tipoArchivo);

		val path = result.getPathArchivo();
		Assert.notNull(path);

		val nombreArchivo = path.getFileName().toString().toUpperCase();
		val terceroCodigo = FilenameUtils.removeExtension(nombreArchivo);

		// @formatter:off
		val configuracion = tipoArchivo
				.getConfiguraciones()
				.stream()
				.filter(a -> a.getCodigo().equals(terceroCodigo))
				.findFirst();
		// @formatter:on
		Assert.isTrue(configuracion.isPresent());

		val campoProductoCodigo = tipoArchivo.getCampoPorCodigo(ProductoPrecio.PRODUCTO_CODIGO);
		Assert.isTrue(campoProductoCodigo.isPresent());

		val campoValorUnitario = tipoArchivo.getCampoPorCodigo(ProductoPrecio.VALOR_UNITARIO);
		Assert.isTrue(campoValorUnitario.isPresent());

		val campoRedondeo = tipoArchivo.getCampoPorCodigo(ProductoPrecio.REDONDEO);
		Assert.isTrue(campoRedondeo.isPresent());

		val clienteCodigo = ClienteCodigoType.HEINZ.toString();
		val terceroIdentificacion = configuracion.get().getValor();

		for (val registro : registros) {
			registro.getDatos().put(ProductoPrecio.CLIENTE_CODIGO, clienteCodigo);
			registro.getDatos().put(ProductoPrecio.TERCERO_IDENTIFICACION, terceroIdentificacion);
			cambiarValorUnitario(registro, campoValorUnitario);
			
			cambiarValoresNoDecimales(registro, campoProductoCodigo);
			cambiarValoresNoDecimales(registro, campoRedondeo);
		}

		return result;
	}

	private void cambiarValorUnitario(final RegistroDTO<ProductoPrecio> registro, final Optional<Campo> campo) {
		String valor = registro.getDatos().get(ProductoPrecio.VALOR_UNITARIO);
		valor = StringUtils.left(valor, campo.get().getNumeroCaracteres());
		registro.getDatos().put(ProductoPrecio.VALOR_UNITARIO, valor);
	}

	private void cambiarValoresNoDecimales(final RegistroDTO<ProductoPrecio> registro, final Optional<Campo> campo) {
		String campoCodigo = campo.get().getCodigo();
		String valor = registro.getDatos().get(campoCodigo);
		valor = StringUtils.replace(valor, ",", ".");
		val index = StringUtils.indexOf(valor, ".");
		if (index > 0) {
			valor = StringUtils.left(valor, index);
			registro.getDatos().put(campoCodigo, valor);
		}
	}
}
