package com.tacticlogistics.integrador.etl.clientes.dicermex;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.clientes.dicermex.model.DicermexSalida;
import com.tacticlogistics.integrador.etl.clientes.dicermex.model.DicermexSalidaRepository;
import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.etl.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.etl.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.etl.handlers.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.etl.handlers.readers.Reader;

@Component
public class SalidasArchivoHandler extends ArchivoHandler<DicermexSalida,Long> {
	private static final Pattern PATTERN = Pattern.compile("(?i:(.*)ESBOrdenesDeDespacho(Parcial)?_(\\d+)\\.txt)");
	
	private static final String CODIGO_TIPO_ARCHIVO = "DICERMEX_SALIDAS";

	@Autowired
	private CharsetDetectorFileReaderBeta reader;

	@Autowired
	private DicermexSalidaRepository repository;

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
	protected Decorator<DicermexSalida> getTransformador() {
		// @formatter:off
		return new MapEntidadSalidaDecorator(
				new CheckRegistrosDuplicadosDecorator<DicermexSalida>(
					new CheckRestriccionesDeCamposDecorator<DicermexSalida>(
						new ExtraerCamposSalidaDecorator(
							new CamposSplitterDecorator<DicermexSalida>(
								new CheckNumeroDeColumnasDecorator<DicermexSalida>(
									new CheckArchivoVacioDecorator<DicermexSalida>(
										new LineasSplitterDecorator<DicermexSalida>(
											new IncluirEncabezadoDecorator<DicermexSalida>(
												new NormalizarSeparadoresDeRegistroDecorator<DicermexSalida>(
													new MayusculasDecorator<DicermexSalida>(
		)))))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<DicermexSalida, Long> getRepository() {
		return repository;
	}
}
