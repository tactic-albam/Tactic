package com.tacticlogistics.integrador.etl.handlers.heinz.maestros;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.etl.decorators.Decorator;
import com.tacticlogistics.integrador.etl.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.etl.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.etl.handlers.heinz.maestros.model.ProductoPrecio;
import com.tacticlogistics.integrador.etl.handlers.heinz.maestros.model.ProductoPrecioRepository;
import com.tacticlogistics.integrador.etl.readers.ExcelWorkSheetReaderGamma;
import com.tacticlogistics.integrador.etl.readers.Reader;

public abstract class ProductoPrecioArchivoHandler extends ArchivoHandler<ProductoPrecio, Long> {

	private static final String CODIGO_TIPO_ARCHIVO = "HEINZ_PRODUCTOS_PRECIOS";
	private static final String WORKSHEET_NAME = "0";

	@Autowired
	private ExcelWorkSheetReaderGamma reader;

	@Autowired
	private ProductoPrecioRepository repository;

	public ProductoPrecioArchivoHandler() {
		super();
	}

	@Override
	protected Reader getReader() {
		if (this.reader.getWorkSheetName() == null) {
			this.reader.setWorkSheetName(WORKSHEET_NAME);
		}
		return reader;
	}

	@Override
	protected String getCodigoTipoArchivo() {
		return CODIGO_TIPO_ARCHIVO;
	}

	@Override
	protected Path getCliente() {
		Path result = Paths.get(ClienteCodigoType.HEINZ.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("MAESTROS\\PRECIOS");
		return result;
	}

	@Override
	protected Decorator<ProductoPrecio> getTransformador() {
		// @formatter:off
		return new MapEntidadProductoPrecioDecorator(
				new CheckRegistrosDuplicadosDecorator<ProductoPrecio>(
					new CheckRestriccionesDeCamposDecorator<ProductoPrecio>(
						new ExtraerCamposProductoPrecioDecorator(
							new CamposSplitterDecorator<ProductoPrecio>(
								new CheckNumeroDeColumnasDecorator<ProductoPrecio>(
									new CheckArchivoVacioDecorator<ProductoPrecio>(
										new LineasSplitterDecorator<ProductoPrecio>(
											new MayusculasDecorator<ProductoPrecio>(
		)))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<ProductoPrecio, Long> getRepository() {
		return repository;
	}
}