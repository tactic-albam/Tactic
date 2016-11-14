package com.tacticlogistics.ext.dicermex.aud.despachos;

import java.sql.Types;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.tacticlogistics.ClienteCodigoType;

@Service
public class AuditoriaService {
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public DataSource dataSource;

	public LocalDateTime getFechaUltimaAuditoria() {
		return FechaUltimaAuditoriaHelper.getFechaUltimaAuditoria(namedParameterJdbcTemplate);
	}

	public LocalDateTime getFechaNuevaAuditoria() {
		return LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
	}

	public int auditar(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// @formatter:off
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource);
		
		simpleJdbcCall
			.withSchemaName("aud")
			.withProcedureName("DespachosDICERMEX")
			.declareParameters(
					new SqlParameter("codigoCliente", Types.VARCHAR),
					new SqlParameter("fechaInicio", Types.TIMESTAMP),
					new SqlParameter("fechaFin", Types.TIMESTAMP),
					new SqlOutParameter("id", Types.INTEGER)
			);

		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("codigoCliente", ClienteCodigoType.DICERMEX.toString())
				.addValue("fechaInicio", fechaInicio)
				.addValue("fechaFin", fechaFin);

		Map<String, Object> result = simpleJdbcCall.execute(in);
		// @formatter:on

		return ((Integer) result.get("id")).intValue();
	}

	// -------------------------------------------------------------------------------------
	private static class FechaUltimaAuditoriaHelper {
		public static LocalDateTime getFechaUltimaAuditoria(NamedParameterJdbcTemplate jdbcTemplate) {
			List<LocalDateTime> list = jdbcTemplate.query(getSql(), getParametros(), getRowMapper());

			LocalDateTime result;
			if (!list.isEmpty()) {
				result = list.get(0).plusSeconds(1);
			} else {
				result = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
			}
			return result;
		}

		private static String getSql() {
			// @formatter:off
			return " " 
					+ " WITH " 
					+ " cte_00 AS " 
					+ " ( " 
					+ " 	SELECT " 
					+ " 		a.fecha_fin, "
					+ " 		ROW_NUMBER() OVER (PARTITION BY a.codigo ORDER BY a.fecha_actualizacion DESC) orden "
					+ " 	FROM aud.auditorias a " 
					+ " 	WHERE " 
					+ " 		a.codigo = :codigoAuditoria " 
					+ " ) "
					+ " SELECT " 
					+ " 	a.fecha_fin " 
					+ " FROM cte_00 a " 
					+ " WHERE " 
					+ " 	a.orden = 1 " 
					+ " ";
			// @formatter:on
		}

		private static Map<String, Object> getParametros() {
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("codigoAuditoria", ClienteCodigoType.DICERMEX.toString());
			return parameters;
		}

		private static RowMapper<LocalDateTime> getRowMapper() {
			return (rs, rowNum) -> {
				return rs.getTimestamp("fecha_fin").toLocalDateTime();
			};
		}
	}
}
