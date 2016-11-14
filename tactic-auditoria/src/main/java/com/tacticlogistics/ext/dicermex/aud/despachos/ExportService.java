package com.tacticlogistics.ext.dicermex.aud.despachos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.tacticlogistics.ClienteCodigoType;
import com.tacticlogistics.ext.dicermex.aud.dto.DetalleDTO;
import com.tacticlogistics.ext.dicermex.aud.dto.ResumenDTO;

@Service
public class ExportService {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");

	@Value("${etl.directorio.raiz}")
	private String directorioRaiz;

	@Value("${etl.directorio.salidas}")
	private String directorioSalida;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Path exportar(int id, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		Path path = getPath(fechaInicio,fechaFin);
		try (Workbook wb = new XSSFWorkbook(); FileOutputStream file = new FileOutputStream(path.toFile())) {
			generar(wb, id);
			wb.write(file);
			return path;
		} catch (IOException e) {
			throw new RuntimeException("Ocurrio un excepción mientras durante la exportación de resultados.", e);
		}
	}

	private Path getPath(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		String timeStamp = LocalDateTime.now().format(formatter);
		String fileName = String.format("AUDITORIA-DICERMEX-DESPACHOS-%s-%s.xlsx", fechaInicio.format(formatter),fechaFin.format(formatter));

		// @formatter:off
		Path path = Paths.get(
				directorioRaiz, 
				ClienteCodigoType.DICERMEX.toString(), 
				directorioSalida,
				timeStamp.substring(0, 6), 
				timeStamp.substring(0, 8),
				fileName);
		// @formatter:on

		if (!Files.exists(path.getParent())) {
			try {
				Files.createDirectories(path.getParent());
			} catch (IOException e) {
				throw new RuntimeException(
						"Ocurrio una excepción al intentar crear el directorio para exportar el archivo "
								+ path.getFileName().toString(),
						e);
			}
		}
		return path;
	}

	private void generar(Workbook wb, int id) {
		List<ResumenDTO> resumen = ResumenHelper.getResumen(namedParameterJdbcTemplate, id);
		(new ResumenSheetHelper()).generar(wb.createSheet("Resumen"), resumen);

		List<DetalleDTO> detalle = DetalleHelper.getDetalle(namedParameterJdbcTemplate, id);
		(new DetalleSheetHelper(wb)).generar(wb.createSheet("Detalle"), detalle);
	}

	// -------------------------------------------------------------------------------------
	private static class ResumenHelper {
		public static List<ResumenDTO> getResumen(NamedParameterJdbcTemplate jdbcTemplate, int id) {
			String sql = getSql();

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("id", id);

			return jdbcTemplate.query(sql, parameters, getRowMapper());
		}

		private static String getSql() {
			// @formatter:off
			return " " 
				+ " WITH "
				+ " cte_00 AS "
				+ " ( "
				+ "     SELECT "
				+ "          CASE  "
				+ "          WHEN a.fecha_archivo = CAST(GETDATE() AS DATE) THEN 'HOY'  "
				+ "          ELSE CAST(a.mes AS VARCHAR) END AS mes "
				+ "         ,sucursal "
				+ "         ,COUNT(1) AS total "
				+ "         ,SUM(a.a) AS a "
				+ "         ,SUM(a.b) AS b "
				+ "         ,SUM(a.c) AS c "
				+ "         ,SUM(a.d) AS d "
				+ "         ,SUM(a.e) AS e "
				+ "         ,SUM(a.f) AS f "
				+ "         ,SUM(a.g) AS g "
				+ "         ,SUM(a.h) AS h "
				+ "         ,SUM(a.i) AS i "
				+ "         ,SUM(a.j) AS j "
				+ "         ,SUM(a.error) AS error "
				+ "     FROM aud.despachos_dicermex a "
				+ "     WHERE "
				+ "         a.id_auditoria = :id "
				+ "     GROUP BY "
				+ "          CASE  "
				+ "          WHEN a.fecha_archivo = CAST(GETDATE() AS DATE) THEN 'HOY'  "
				+ "          ELSE CAST(a.mes AS VARCHAR) END, "
				+ "          sucursal "
				+ " ) "
				+ " SELECT "
				+ "     * "
				+ " FROM cte_00 a "
				+ " ORDER BY "
				+ "     a.mes,a.sucursal "
				+ " ";
			// @formatter:on
		}

		private static RowMapper<ResumenDTO> getRowMapper() {
			return (rs, rowNum) -> {
				// @formatter:off
				return ResumenDTO
						.builder()
						.mes(rs.getString("mes"))
						.sucursal(rs.getString("sucursal"))
						.total(rs.getInt("total"))
						.a(rs.getInt("a"))
						.b(rs.getInt("b"))
						.c(rs.getInt("c"))
						.d(rs.getInt("d"))
						.e(rs.getInt("e"))
						.f(rs.getInt("f"))
						.g(rs.getInt("g"))
						.h(rs.getInt("h"))
						.i(rs.getInt("i"))
						.j(rs.getInt("j"))
						.build();
				// @formatter:on
			};
		}
	}

	private static class DetalleHelper {
		public static List<DetalleDTO> getDetalle(NamedParameterJdbcTemplate jdbcTemplate, int id) {
			String sql = getSql();

			Map<String, Object> parameters = new HashMap<>();
			parameters.put("id", id);

			return jdbcTemplate.query(sql, parameters, getRowMapper());
		}

		private static String getSql() {
			// @formatter:off
			return " " 
			+" SELECT "
			+"      id_orden "
			+"     ,numero_orden "
			+"     ,a "
			+"     ,b "
			+"     ,c "
			+"     ,d "
			+"     ,e "
			+"     ,f "
			+"     ,g "
			+"     ,h "
			+"     ,i "
			+"     ,j "
			+"     ,fecha_archivo "
			+"     ,sucursal "
			+"     ,tipo_documento "
			+"     ,canal "
			+"     ,destinatario_identificacion "
			+"     ,destinatario_nombre "
			+"     ,fecha_entrega_sugerida_minima AS fecha_minima "
			+"     ,fecha_entrega_sugerida_maxima AS fecha_maxima "
			+"     ,hora_entrega_sugerida_minima AS hora_minima "
			+"     ,hora_entrega_sugerida_maxima AS hora_maxima "
			+"     ,notas "
			+"     ,ciudad "
			+"     ,destino_direccion "
			+"     ,destino_nombre "
			+" FROM aud.despachos_dicermex a "
			+" WHERE "
			+"     a.id_auditoria = :id "
			+" AND a.error = 1 "
			+" ORDER BY  "
			+"      fecha_archivo "
			+"     ,sucursal "
			+"     ,canal "
			+"     ,tipo_documento "
			+ " ";
			// @formatter:on
		}

		private static RowMapper<DetalleDTO> getRowMapper() {
			return (rs, rowNum) -> {
				// @formatter:off
				Date fechaMinima = rs.getDate("fecha_minima");
				Date fechaMaxima = rs.getDate("fecha_maxima");
				Time horaMinima = rs.getTime("hora_minima");
				Time horaMaxima = rs.getTime("hora_maxima");
				
				return DetalleDTO
						.builder()
						.id(rs.getInt("id_orden"))
						.numeroOrden(rs.getString("numero_orden"))
						.a(rs.getInt("a"))
						.b(rs.getInt("b"))
						.c(rs.getInt("c"))
						.d(rs.getInt("d"))
						.e(rs.getInt("e"))
						.f(rs.getInt("f"))
						.g(rs.getInt("g"))
						.h(rs.getInt("h"))
						.i(rs.getInt("i"))
						.j(rs.getInt("j"))
						.fechaArchivo(rs.getDate("fecha_archivo").toLocalDate())
						.sucursal(rs.getString("sucursal"))
						.tipoDocumento(rs.getString("tipo_documento"))
						.canal(rs.getString("canal"))
						.destinatarioIdentificacion(rs.getString("destinatario_identificacion"))
						.destinatarioNombre(rs.getString("destinatario_nombre"))
						.fechaMinima(fechaMinima==null?null:fechaMinima.toLocalDate())
						.fechaMaxima(fechaMaxima==null?null:fechaMaxima.toLocalDate())
						.horaMinima(horaMinima==null?null:horaMinima.toLocalTime())
						.horaMaxima(horaMaxima==null?null:horaMaxima.toLocalTime())
						.notas(rs.getString("notas"))
						.ciudad(rs.getString("ciudad"))
						.destinoDireccion(rs.getString("destino_direccion"))
						.destinoNombre(rs.getString("destino_nombre"))
						.build();
				// @formatter:on
			};
		}
	}

	// -------------------------------------------------------------------------------------
	private abstract class SheetHelper<T> {
		public int generar(Sheet sheet, List<T> datos) {
			int i = 0;
			generarEncabezados(sheet.createRow(i++));
			for (T e : datos) {
				generaRegistro(sheet.createRow(i++), e);
			}
			return i;
		}

		protected void generarEncabezados(Row row) {
			String[] columnas = getEncabezados();
			for (int col = 0; col < columnas.length; col++) {
				row.createCell(col).setCellValue(columnas[col]);
			}
		}

		abstract protected String[] getEncabezados();

		abstract protected void generaRegistro(Row row, T dto);
	}

	private class ResumenSheetHelper extends SheetHelper<ResumenDTO> {

		public int generar(Sheet sheet, List<ResumenDTO> datos) {
			int i = super.generar(sheet, datos);
			i++;
			i++;
			return generarConvenciones(sheet, i);
		}

		@Override
		protected String[] getEncabezados() {
			return new String[] { "Mes", "Sucursal", "Total", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		}

		@Override
		protected void generaRegistro(Row row, ResumenDTO dto) {
			int col = 0;
			row.createCell(col++).setCellValue(dto.getMes());
			row.createCell(col++).setCellValue(dto.getSucursal());
			row.createCell(col++).setCellValue(dto.getTotal());
			row.createCell(col++).setCellValue(dto.getA());
			row.createCell(col++).setCellValue(dto.getB());
			row.createCell(col++).setCellValue(dto.getC());
			row.createCell(col++).setCellValue(dto.getD());
			row.createCell(col++).setCellValue(dto.getE());
			row.createCell(col++).setCellValue(dto.getF());
			row.createCell(col++).setCellValue(dto.getG());
			row.createCell(col++).setCellValue(dto.getH());
			row.createCell(col++).setCellValue(dto.getI());
			row.createCell(col++).setCellValue(dto.getJ());
		}

		private int generarConvenciones(Sheet sheet, int i) {
			List<String> convenciones = getConvenciones();
			for (String e : convenciones) {
				sheet.createRow(i++).createCell(0).setCellValue(e);
			}
			return i;
		}

		private List<String> getConvenciones() {
			List<String> list = new ArrayList<String>();
			list.add(
					"a.      El intervalo de fechas de entrega que remite el cliente no es legible en el formato acordado o no fueron suministradas");
			list.add(
					"b.      El tipo del documento de entrega es TRS y la dirección de entrega corresponde a  CALLE 17 42A 54");
			list.add("c.      No se suministró un código de ciudad destino valido");
			list.add("d.      No se suministró una dirección de destino (Dirección vacía)");
			list.add(
					"e.      Se suministró un intervalo de fechas de entrega, pero el intervalo horario no es legible en el formato acordado o no fue suministrado.");
			list.add("f.      Hora mínima y máxima de entrega iguales");
			list.add("g.      Fecha de entrega mínima, mayor que la máxima");
			list.add("h.      Hora de entrega mínima mayor que la máxima");
			list.add("i.      Intervalo horario de entrega anterior a las 6 AM o posterior a las 6 PM");
			list.add(
					"j.      Fechas de entregas deducidas por Tactic: Son las solicitudes que tengan cualquiera de las siguientes palabras y solo estas en la observaciones de la entrega: ENTREGAR|LUNES|MARTES|MIERCOLES|JUEVES|VIERNES|SABADO|DOMINGO|AM|PM");
			return list;
		}

	}

	private class DetalleSheetHelper extends SheetHelper<DetalleDTO> {
		private CellStyle cellStyleDate;
		private CellStyle cellStyleTime;

		@SuppressWarnings("unused")
		protected DetalleSheetHelper() {

		}

		public DetalleSheetHelper(Workbook wb) {
			CreationHelper createHelper = wb.getCreationHelper();
			CellStyle cellStyleDate = wb.createCellStyle();
			cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("yyyymmdd"));
			CellStyle cellStyleTime = wb.createCellStyle();
			cellStyleTime.setDataFormat(createHelper.createDataFormat().getFormat("hh:mm"));
		}

		@Override
		protected String[] getEncabezados() {
			// @formatter:off
			return new String[]{
					"id_orden",
					"numero_orden",
					"ACCION",
					"FEMI (AAAAMMDD)",
					"FEMA (AAAAMMDD)",
					"HOMI (HH:MM)",
					"HOMA (HH:MM)",
					"CIUDAD",
					"DIRECCION",
					"A",
					"B",
					"C",
					"D",
					"E",
					"F",
					"G",
					"H",
					"I",
					"J",
					"fecha_archivo",
					"sucursal",
					"tipo_documento",
					"canal",
					"destinatario_identificacion",
					"destinatario_nombre",
					"fecha_minima",
					"fecha_maxima",
					"hora_minima",
					"hora_maxima",
					"notas",
					"ciudad_destino",
					"destino_direccion",
					"destino_nombre"
			};
			// @formatter:on
		}

		@Override
		protected void generaRegistro(Row row, DetalleDTO dto) {
			int col = 0;
			row.createCell(col++).setCellValue(dto.getId());
			row.createCell(col++).setCellValue(dto.getNumeroOrden());
			row.createCell(col++).setCellValue("");
			row.createCell(col++).setCellValue("");
			row.getCell(col - 1).setCellStyle(cellStyleDate);
			row.createCell(col++).setCellValue("");
			row.getCell(col - 1).setCellStyle(cellStyleDate);
			row.createCell(col++).setCellValue("");
			row.getCell(col - 1).setCellStyle(cellStyleTime);
			row.createCell(col++).setCellValue("");
			row.getCell(col - 1).setCellStyle(cellStyleTime);
			row.createCell(col++).setCellValue("");
			row.createCell(col++).setCellValue("");
			row.createCell(col++).setCellValue(dto.getA());
			row.createCell(col++).setCellValue(dto.getB());
			row.createCell(col++).setCellValue(dto.getC());
			row.createCell(col++).setCellValue(dto.getD());
			row.createCell(col++).setCellValue(dto.getE());
			row.createCell(col++).setCellValue(dto.getF());
			row.createCell(col++).setCellValue(dto.getG());
			row.createCell(col++).setCellValue(dto.getH());
			row.createCell(col++).setCellValue(dto.getI());
			row.createCell(col++).setCellValue(dto.getJ());
			row.createCell(col++).setCellValue(Date.valueOf(dto.getFechaArchivo()));
			row.getCell(col - 1).setCellStyle(cellStyleDate);
			row.createCell(col++).setCellValue(dto.getSucursal());
			row.createCell(col++).setCellValue(dto.getTipoDocumento());
			row.createCell(col++).setCellValue(dto.getCanal());
			row.createCell(col++).setCellValue(dto.getDestinatarioIdentificacion());
			row.createCell(col++).setCellValue(dto.getDestinatarioNombre());
			if (dto.getFechaMinima() != null) {
				row.createCell(col++).setCellValue(Date.valueOf(dto.getFechaMinima()));
			} else {
				row.createCell(col++);
			}
			row.getCell(col - 1).setCellStyle(cellStyleDate);
			if (dto.getFechaMaxima() != null) {
				row.createCell(col++).setCellValue(Date.valueOf(dto.getFechaMaxima()));
			} else {
				row.createCell(col++);
			}
			row.getCell(col - 1).setCellStyle(cellStyleDate);
			if (dto.getHoraMinima() != null) {
				row.createCell(col++).setCellValue(Time.valueOf(dto.getHoraMinima()));
			} else {
				row.createCell(col++);
			}
			row.getCell(col - 1).setCellStyle(cellStyleTime);
			if (dto.getHoraMaxima() != null) {
				row.createCell(col++).setCellValue(Time.valueOf(dto.getHoraMaxima()));
			} else {
				row.createCell(col++);
			}
			row.getCell(col - 1).setCellStyle(cellStyleTime);
			row.createCell(col++).setCellValue(dto.getNotas());
			row.createCell(col++).setCellValue(dto.getCiudad());
			row.createCell(col++).setCellValue(dto.getDestinoDireccion());
			row.createCell(col++).setCellValue(dto.getDestinoNombre());
		}
	}
}
