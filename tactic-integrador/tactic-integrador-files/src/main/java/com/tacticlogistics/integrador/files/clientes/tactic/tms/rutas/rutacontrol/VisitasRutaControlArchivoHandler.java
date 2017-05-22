package com.tacticlogistics.integrador.files.clientes.tactic.tms.rutas.rutacontrol;

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
import com.tacticlogistics.integrador.files.handlers.readers.ExcelWorkSheetReaderDelta;
import com.tacticlogistics.integrador.files.handlers.readers.Reader;
import com.tacticlogistics.integrador.model.tms.rutas.rutacontrol.VisitaRutaControl;
import com.tacticlogistics.integrador.model.tms.rutas.rutacontrol.VisitaRutaControlRepository;

@Component
public class VisitasRutaControlArchivoHandler extends ArchivoHandler<VisitaRutaControl,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "RUTACONTROL_VISITAS";

	private static final String WORKSHEET_NAME = "Resumen_Diario";

	@Autowired
	private ExcelWorkSheetReaderDelta reader;

	@Autowired
	private VisitaRutaControlRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected Reader getReader() {
		if (this.reader.getWorkSheetName() == null) {
			this.reader.setWorkSheetName(WORKSHEET_NAME);
			this.reader.setRowOffset(6);
			this.reader.setColOffset(1);
			this.reader.setLastCellNum(15);
		}
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
		Path result = Paths.get("TMS\\RUTAS\\RUTACONTROL\\VISITAS");
		return result;
	}

	@Override
	protected Pattern getFileNamePattern() {
		return PATTERN_XLS;
	}
	
	@Override
	protected Decorator<VisitaRutaControl> getTransformador() {
		// @formatter:off
		return new MapEntidadVisitaRutaControlDecorator(
				new CheckRegistrosDuplicadosDecorator<VisitaRutaControl>(
					new CheckRestriccionesDeCamposDecorator<VisitaRutaControl>(
						new ReemplazarValoresVisitaRutaControlDecorator(
							new CamposSplitterDecorator<VisitaRutaControl>(
								new CheckNumeroDeColumnasDecorator<VisitaRutaControl>(
									new CheckArchivoVacioDecorator<VisitaRutaControl>(
										new LineasSplitterDecorator<VisitaRutaControl>(
											new IncluirEncabezadoDecorator<VisitaRutaControl>(
												new NormalizarSeparadoresDeRegistroDecorator<VisitaRutaControl>(
													new MayusculasDecorator<VisitaRutaControl>(
		)))))))))));
		// @formatter:on
	}

	@Override
	protected JpaRepository<VisitaRutaControl, Long> getRepository() {
		return repository;
	}
}
