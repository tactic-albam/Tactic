package com.tacticlogistics.integrador.files.clientes.tactic.tms.rutas.toursolver;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.model.etl.registro.EstadoRegistroType;
import com.tacticlogistics.integrador.model.tms.rutas.toursolver.RutaTourSolver;

import lombok.val;

public class MapEntidadRutaTourSolverDecorator extends MapEntidadDecorator<RutaTourSolver> {

	public MapEntidadRutaTourSolverDecorator() {
		super();
	}

	public MapEntidadRutaTourSolverDecorator(Filter<RutaTourSolver> inner) {
		super(inner);
	}

	@Override
	protected RutaTourSolver map(ArchivoDTO<RutaTourSolver> archivoDTO, RegistroDTO<RutaTourSolver> registro) {
		val tipoArchivo = archivoDTO.getTipoArchivo();
		Assert.notNull(tipoArchivo);
		val archivo = archivoDTO.getArchivo();
		Assert.notNull(archivo);
		val datos = registro.getDatos();
		Assert.notEmpty(datos);

		long idOrden = getInteger(tipoArchivo, datos, RutaTourSolver.ID_ORDEN);
		LocalDate fecha = getLocalDate(tipoArchivo, datos, RutaTourSolver.FECHA_ESTIMADA_ENTREGA);
		LocalTime hora = getLocalTimeFromBigDecimal(tipoArchivo, datos, RutaTourSolver.HORA_ESTIMADA_ENTREGA);

		// @formatter:off
		val result = RutaTourSolver.builder()
				.idArchivo(archivo.getId())
				.estadoRegistro(EstadoRegistroType.ESTRUCTURA_VALIDADA)
				.numeroLinea(registro.getNumeroLinea())
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.fechaActualizacion(archivo.getFechaCreacion())
				.usuarioActualizacion(archivo.getUsuarioCreacion())
				
				.clienteCodigo(datos.get(RutaTourSolver.CLIENTE_CODIGO))
				.idOrden(idOrden)
				.placa(datos.get(RutaTourSolver.PLACA))
				.fechaEstimadaEntrega(fecha)
				.horaEstimadaEntrega(hora)
				.particularidades(datos.get(RutaTourSolver.PARTICULARIDADES))
				.build();
		// @formatter:on

		return result;
	}
}