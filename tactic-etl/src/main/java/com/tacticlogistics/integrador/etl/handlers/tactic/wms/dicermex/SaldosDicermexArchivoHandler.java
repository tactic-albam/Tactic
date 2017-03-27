package com.tacticlogistics.integrador.etl.handlers.tactic.wms.dicermex;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.etl.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.etl.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.etl.decorators.Decorator;
import com.tacticlogistics.integrador.etl.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.etl.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.etl.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.etl.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.etl.handlers.tactic.wms.dicermex.model.SaldoDicermex;
import com.tacticlogistics.integrador.etl.handlers.tactic.wms.dicermex.model.SaldoDicermexRepository;
import com.tacticlogistics.integrador.etl.readers.CharsetDetectorFileReaderBeta;
import com.tacticlogistics.integrador.etl.readers.ExcelWorkSheetReaderBeta;
import com.tacticlogistics.integrador.etl.readers.ExcelWorkSheetReaderGamma;
import com.tacticlogistics.integrador.etl.readers.Reader;

@Component
public class SaldosDicermexArchivoHandler extends ArchivoHandler<SaldoDicermex,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "DICERMEX_SALDOS";

	@Autowired
	private CharsetDetectorFileReaderBeta reader;

	@Autowired
	private SaldoDicermexRepository repository;

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
		Path result = Paths.get(ClienteCodigoType.TACTIC.toString());
		return result;
	}

	@Override
	protected Path getSubDirectorioRelativo() {
		Path result = Paths.get("WMS\\DICERMEX\\SALDOS");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_TXT;
	}
	
	@Override
	protected Decorator<SaldoDicermex> getTransformador() {
		// @formatter:off
		return new MapEntidadSaldoDicermexDecorator(
				new CheckRegistrosDuplicadosDecorator<SaldoDicermex>(
					new CheckRestriccionesDeCamposDecorator<SaldoDicermex>(
						new CamposSplitterDecorator<SaldoDicermex>(
							new CheckNumeroDeColumnasDecorator<SaldoDicermex>(
								new CheckArchivoVacioDecorator<SaldoDicermex>(
									new LineasSplitterDecorator<SaldoDicermex>(
										new NormalizarSeparadoresDeRegistroDecorator<SaldoDicermex>(
											new MayusculasDecorator<SaldoDicermex>(
		)))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<SaldoDicermex, Long> getRepository() {
		return repository;
	}
}
