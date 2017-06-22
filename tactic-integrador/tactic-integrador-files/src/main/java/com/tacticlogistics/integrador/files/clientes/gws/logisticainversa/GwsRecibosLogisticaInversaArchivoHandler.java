package com.tacticlogistics.integrador.files.clientes.gws.logisticainversa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.clientes.tactic.oms.recibos.MapEntidadReciboDecorator;
import com.tacticlogistics.integrador.files.handlers.ArchivoPlanoHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.model.oms.Recibo;
import com.tacticlogistics.integrador.model.oms.ReciboRepository;

@Component
public class GwsRecibosLogisticaInversaArchivoHandler extends ArchivoPlanoHandler<Recibo,Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "GWS_LOGISTICA_INVERSA";

	private static final String SUBDIRECTORIO_RELATIVO = "ORDENES\\LOGISTICA_INVERSA";

	@Autowired
	private ReciboRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected String getClienteCodigo() {
		return ClienteCodigoType.GWS.toString();
	}
	
	@Override
	protected String getCodigoTipoArchivo() {
		return CODIGO_TIPO_ARCHIVO;
	}
	
	@Override
	protected String getDirectorioRelativo() {
		return SUBDIRECTORIO_RELATIVO;
	}

	@Override
	protected JpaRepository<Recibo, Long> getRepository() {
		return repository;
	}

	@Override
	protected Decorator<Recibo> getTransformador() {
		// @formatter:off
		return new MapEntidadReciboDecorator(
				new CheckRegistrosDuplicadosDecorator<Recibo>(
					new CheckRestriccionesDeCamposDecorator<Recibo>(
						new EnriquecerCamposDecorator(
							new IncluirCamposDecorator<Recibo>(
								new CamposSplitterDecorator<Recibo>(
									new CheckNumeroDeColumnasDecorator<Recibo>(
										new CheckArchivoVacioDecorator<Recibo>(
											new LineasSplitterDecorator<Recibo>(
												new NormalizarSeparadoresDeRegistroDecorator<Recibo>(
													new MayusculasDecorator<Recibo>(
				)))))))))));
		// @formatter:on
	}
}