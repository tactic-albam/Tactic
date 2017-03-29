package com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.toursolver;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.clientes.tactic.tms.rutas.toursolver.model.RutaTourSolver;
import com.tacticlogistics.integrador.etl.handlers.decorators.Filter;
import com.tacticlogistics.integrador.etl.handlers.decorators.MapEntidadDecorator;
import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;

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

		int secuencia = getInteger(tipoArchivo, datos, RutaTourSolver.SECUENCIA);
		long idOrden = getInteger(tipoArchivo, datos, RutaTourSolver.ID_ORDEN);
		LocalDate fecha = getLocalDate(tipoArchivo, datos, RutaTourSolver.FECHA_ESTIMADA_ENTREGA);
		LocalTime hora = getLocalTimeFromBigDecimal(tipoArchivo, datos, RutaTourSolver.HORA_ESTIMADA_ENTREGA);

		// @formatter:off
		val result = 
				RutaTourSolver.builder()
				.idArchivo(archivo.getId())
				.clienteCodigo(datos.get(RutaTourSolver.CLIENTE_CODIGO))
				.idOrden(idOrden)
				.placa(datos.get(RutaTourSolver.PLACA))
				.secuencia(secuencia)
				.fechaEstimadaEntrega(fecha)
				.horaEstimadaEntrega(hora)
				.linea(registro.getLinea())
				.fechaCreacion(archivo.getFechaCreacion())
				.usuarioCreacion(archivo.getUsuarioCreacion())
				.build();
		// @formatter:on

		return result;
	}
}