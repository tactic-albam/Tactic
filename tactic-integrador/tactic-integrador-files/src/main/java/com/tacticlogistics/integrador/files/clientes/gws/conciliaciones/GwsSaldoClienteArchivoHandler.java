package com.tacticlogistics.integrador.files.clientes.gws.conciliaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.files.handlers.ArchivoPlanoHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.CamposSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckArchivoVacioDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckNumeroDeColumnasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRegistrosDuplicadosDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.CheckRestriccionesDeCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirCamposDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirEncabezadoDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.model.wms.conciliaciones.SaldoCliente;
import com.tacticlogistics.integrador.model.wms.conciliaciones.SaldoClienteRepository;

@Component
public class GwsSaldoClienteArchivoHandler extends ArchivoPlanoHandler<SaldoCliente, Long> {
	private static final String CODIGO_TIPO_ARCHIVO = "SALDOS_CLIENTE";

	private static final String SUBDIRECTORIO_RELATIVO = "CONCILIACIONES";

	@Autowired
	private SaldoClienteRepository repository;

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
	protected JpaRepository<SaldoCliente, Long> getRepository() {
		return repository;
	}

	@Override
	protected Decorator<SaldoCliente> getTransformador() {
		// @formatter:off
		return new MapEntidadSaldoClienteDecorator(
				new CheckRegistrosDuplicadosDecorator<SaldoCliente>(
					new CheckRestriccionesDeCamposDecorator<SaldoCliente>(
						new IncluirCamposDecorator<SaldoCliente>(
							new CamposSplitterDecorator<SaldoCliente>(
								new CheckNumeroDeColumnasDecorator<SaldoCliente>(
									new CheckArchivoVacioDecorator<SaldoCliente>(
										new LineasSplitterDecorator<SaldoCliente>(
											new IncluirEncabezadoDecorator<SaldoCliente>(
												new MayusculasDecorator<SaldoCliente>())))))))));
		// @formatter:on
	}
}
