package com.tacticlogistics.integrador.files.clientes.heinz.cadenas;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.files.handlers.decorators.IncluirCamposDecorator;
import com.tacticlogistics.integrador.model.oms.Salida;

import lombok.Builder;
import lombok.Data;
import lombok.val;

public class EnriquecerCamposDecorator extends Decorator<Salida> {

	private static final String CANTIDAD_PUNTO = "CANTIDAD_PUNTO";
	private static final String CROSS_DOCKING_CONSOLIDADO = "CROSS-DOCKING CONSOLIDADO";

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public EnriquecerCamposDecorator() {
		super();
	}

	public EnriquecerCamposDecorator(Filter<Salida> inner) {
		super(inner);
	}

	public EnriquecerCamposDecorator(NamedParameterJdbcTemplate jdbcTemplate, IncluirCamposDecorator<Salida> inner) {
		super(inner);
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ArchivoDTO<Salida> transformar(ArchivoDTO<Salida> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Validate.notEmpty(result.getRegistros());

		val puntos = getPuntos();
		val mapCiudades = puntos.stream().collect(Collectors.toMap(Entry::getPuntoCodigo, Entry::getCiudadCodigo));

		val registros = result.getRegistros();

		for (val registro : registros) {
			val servicioCodigo = registro.getDatos().get(Salida.SERVICIO_CODIGO);
			if (CROSS_DOCKING_CONSOLIDADO.equals(servicioCodigo)) {
				registro.getDatos().put(Salida.CANTIDAD, registro.getDatos().get(CANTIDAD_PUNTO));
			}
			
			val puntoCodigo = registro.getDatos().get(Salida.PUNTO_CODIGO);
			val ciudadCodigo = mapCiudades.get(puntoCodigo);
			val bodegaCodigo = puntoCodigo;

			registro.getDatos().put(Salida.CIUDAD_CODIGO, ciudadCodigo);
			registro.getDatos().put(Salida.BODEGA_ORIGEN_CODIGO, bodegaCodigo);
		}
		return result;
	}

	protected List<Entry> getPuntos() {
		List<Entry> result;

		val sql = getSQLCodigos();
		val parametros = new MapSqlParameterSource();
		parametros.addValue("codigoCliente", ClienteCodigoType.HEINZ.toString());
		result = jdbcTemplate.query(sql, parametros, getRowMapper());
		return result;
	}

	protected String getSQLCodigos() {
		// @formatter:off
		val result = "  " 
		 + " SELECT " 
		 + "     c.codigo AS punto_codigo, " 
		 + "     d.codigo AS ciudad_codigo " 
		 + " FROM dbo.crm_clientes a " 
		 + " INNER JOIN dbo.crm_terceros b ON " 
		 + "     b.id_cliente = a.id_cliente " 
		 + " INNER JOIN dbo.crm_puntos c ON " 
		 + "     c.id_tercero = b.id_tercero " 
		 + " INNER JOIN dbo.geo_ciudades d ON " 
		 + "     d.id_ciudad = c.id_ciudad " 
		 + " WHERE " 
		 + "     a.codigo = :codigoCliente " 
		 + "  ";
		// @formatter:on

		return result;
	}

	protected RowMapper<Entry> getRowMapper() {
		// @formatter:off
		return (rs, rowNum) -> {
			Entry result = Entry
				.builder()
				.puntoCodigo(rs.getString("punto_codigo"))
				.ciudadCodigo(rs.getString("ciudad_codigo"))
				.build();
			return result;
		};
		// @formatter:on
	}

	@Data
	@Builder
	static class Entry {
		private String puntoCodigo;
		private String ciudadCodigo;
	}
}