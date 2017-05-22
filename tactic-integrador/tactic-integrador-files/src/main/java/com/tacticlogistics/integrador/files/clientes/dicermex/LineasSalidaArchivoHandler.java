package com.tacticlogistics.integrador.files.clientes.dicermex;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.files.handlers.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.files.handlers.readers.Reader;
import com.tacticlogistics.integrador.model.clientes.dicermex.DicermexLineaSalida;
import com.tacticlogistics.integrador.model.clientes.dicermex.DicermexLineaSalidaRepository;

@Component
public class LineasSalidaArchivoHandler extends ArchivoHandler<DicermexLineaSalida,Long> {
	private static final Pattern PATTERN = Pattern.compile("(?i:(.*)ESBOrdenesDeDespacho(Parcial)?_(\\d+)_DETALLE\\.txt)");
	
	private static final String CODIGO_TIPO_ARCHIVO = "DICERMEX_LINEAS_SALIDA";

	@Autowired
	private CharsetDetectorFileReaderBeta reader;

	@Autowired
	private DicermexLineaSalidaRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected Reader getReader() {
		return reader;
	}

	@Override
	protected String getCodigoTipoArchivo() {
		return CODIGO_TIPO_ARCHIVO;
	}

	@Override
	protected Path getCliente() {
		Path result = Paths.get(ClienteCodigoType.DICERMEX.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("SALIDAS");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN;
	}
	
	@Override
	protected Decorator<DicermexLineaSalida> getTransformador() {
		// @formatter:off
		return new MapEntidadLineaSalidaDecorator(
				new CheckRegistrosDuplicadosDecorator<DicermexLineaSalida>(
					new CheckRestriccionesDeCamposDecorator<DicermexLineaSalida>(
						new CamposSplitterDecorator<DicermexLineaSalida>(
							new CheckNumeroDeColumnasDecorator<DicermexLineaSalida>(
								new CheckArchivoVacioDecorator<DicermexLineaSalida>(
									new LineasSplitterDecorator<DicermexLineaSalida>(
										new IncluirEncabezadoDecorator<DicermexLineaSalida>(
											new NormalizarSeparadoresDeRegistroDecorator<DicermexLineaSalida>(
												new MayusculasDecorator<DicermexLineaSalida>(
		))))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<DicermexLineaSalida, Long> getRepository() {
		return repository;
	}
}
