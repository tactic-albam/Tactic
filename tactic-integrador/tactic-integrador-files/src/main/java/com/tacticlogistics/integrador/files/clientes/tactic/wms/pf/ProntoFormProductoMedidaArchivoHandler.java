package com.tacticlogistics.integrador.files.clientes.tactic.wms.pf;

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
import com.tacticlogistics.integrador.files.handlers.decorators.LineasSplitterDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.MayusculasDecorator;
import com.tacticlogistics.integrador.files.handlers.decorators.NormalizarSeparadoresDeRegistroDecorator;
import com.tacticlogistics.integrador.model.wms.pf.ProntoFormProductoMedida;
import com.tacticlogistics.integrador.model.wms.pf.ProntoFormProductoMedidaRepository;

@Component
public class ProntoFormProductoMedidaArchivoHandler extends ArchivoPlanoHandler<ProntoFormProductoMedida,Long> {
	public static final String CODIGO_TIPO_ARCHIVO = "WMS_PF_PRODUCTOS_MEDIDAS";

	private static final String SUBDIRECTORIO_RELATIVO = "cedis/huellas";

	@Autowired
	private ProntoFormProductoMedidaRepository repository;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected String getClienteCodigo() {
		return ClienteCodigoType.EGAKAT.toString();
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
	protected JpaRepository<ProntoFormProductoMedida, Long> getRepository() {
		return repository;
	}
	
	@Override
	protected Decorator<ProntoFormProductoMedida> getTransformador() {
		// @formatter:off
		return new MapEntidadProntoFormProductoMedidaDecorator(
				new CheckRegistrosDuplicadosDecorator<ProntoFormProductoMedida>(
					new CheckRestriccionesDeCamposDecorator<ProntoFormProductoMedida>(
						new CamposSplitterDecorator<ProntoFormProductoMedida>(
							new CheckNumeroDeColumnasDecorator<ProntoFormProductoMedida>(
								new CheckArchivoVacioDecorator<ProntoFormProductoMedida>(
									new LineasSplitterDecorator<ProntoFormProductoMedida>(
										new NormalizarSeparadoresDeRegistroDecorator<ProntoFormProductoMedida>(
											new MayusculasDecorator<ProntoFormProductoMedida>()))))))));
		// @formatter:on
	}
}
