package com.tacticlogistics.integrador.etl.handlers.readers;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tacticlogistics.integrador.etl.handlers.ArchivoHandler;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Component
@Scope(SCOPE_PROTOTYPE)
@Getter
@Setter
public class ExcelWorkSheetReaderDelta implements Reader {
	public static final char NON_BREAKING_SPACE = 160;

	@NonNull
	private String workSheetName;

	@Override
	public String read(Path archivo) throws IOException {
		// http://stackoverflow.com/questions/4929646/how-to-get-an-excel-blank-cell-value-in-apache-poi
		Workbook workbook = createWorkBook(archivo);
		Sheet sheet = getWorkSheet(workbook);

		String result = getData(sheet);
		ArchivoHandler.write(result);

		workbook.close();
		return result;
	}

	private Workbook createWorkBook(Path archivo) throws FileNotFoundException, IOException {
		Workbook workbook;
		InputStream in = new FileInputStream(archivo.toFile());
		try {
			workbook = WorkbookFactory.create(in);
		} catch (EncryptedDocumentException e1) {
			throw new RuntimeException(e1);
		} catch (InvalidFormatException e1) {
			throw new RuntimeException(e1);
		}
		return workbook;
	}

	private Sheet getWorkSheet(Workbook workbook) {
		Sheet sheet;
		try {
			int index = Integer.valueOf(this.getWorkSheetName());
			sheet = workbook.getSheetAt(index);
		} catch (NumberFormatException e) {
			sheet = workbook.getSheet(this.getWorkSheetName());
		}
		return sheet;
	}

	@SuppressWarnings("deprecation")
	private String getData(Sheet sheet) {
		DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DataFormatter formatter = new DataFormatter();
		StringBuilder sb = new StringBuilder();

		for (Row row : sheet) {
			for (Cell cell : row) {
				// get the text that appears in the cell by getting the cell
				// value and applying any data formats (Date, 0.00, 1.23e9,
				// $1.23, etc)
				String text = formatter.formatCellValue(cell);

				// Alternatively, get the value and format it yourself
				switch (cell.getCellTypeEnum()) {
				case STRING:
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
						text = ldt.format(dateTimeformatter);
						text = StringUtils.remove(text, " 00:00:00");
					}
					break;
				case BOOLEAN:
					break;
				case FORMULA:
					break;
				case BLANK:
					break;
				default:
				}

				sb.append(text).append("\t");
			}
			if (sb.length() > 0) {
				sb.setLength(sb.length() - 1);
			}
			sb.append("\n");
		}

		String result = sb.toString();
		return result;
	}
}
