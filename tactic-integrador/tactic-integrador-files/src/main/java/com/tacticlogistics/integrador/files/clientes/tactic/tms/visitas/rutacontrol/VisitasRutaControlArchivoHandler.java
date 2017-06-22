package com.tacticlogistics.integrador.files.clientes.tactic.tms.visitas.rutacontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.handlers.ArchivoExcelHandler;
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
import com.tacticlogistics.integrador.model.tms.visitas.rutacontrol.VisitaRutaControl;
import com.tacticlogistics.integrador.model.tms.visitas.rutacontrol.VisitaRutaControlRepository;

import lombok.val;

@Component
public class VisitasRutaControlArchivoHandler extends ArchivoExcelHandler<VisitaRutaControl, Long> {
	private static final String WORKSHEET_NAME = "Resumen_Diario";

	private static final String CODIGO_TIPO_ARCHIVO = "RUTACONTROL_VISITAS";

	private static final String SUBDIRECTORIO_RELATIVO = "TMS\\VISITAS\\RUTACONTROL";

	@Autowired
	private VisitaRutaControlRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected String getWorkSheetName() {
		return WORKSHEET_NAME;
	}

	@Override
	protected String getClienteCodigo() {
		return ClienteCodigoType.TACTIC.toString();
	}
	
	@Override
	protected String getCodigoTipoArchivo() {
		return CODIGO_TIPO_ARCHIVO;
	}

	@Override
	protected String getDirectorioRelativo() {
		return SUBDIRECTORIO_RELATIVO;
	}

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected Reader getReader() {
		val reader = (ExcelWorkSheetReaderDelta) super.getReader();
		reader.setRowOffset(6);
		reader.setColOffset(1);
		reader.setLastCellNum(20);
		return reader;
	}

	@Override
	protected JpaRepository<VisitaRutaControl, Long> getRepository() {
		return repository;
	}

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
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
}
