package com.tacticlogistics.integrador.files.handlers.readers;

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

import org.apache.commons.lang3.StringUtils;
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

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

@Component
@Scope(SCOPE_PROTOTYPE)
@Getter
@Setter
public class ExcelWorkSheetReaderDelta implements Reader {
	public static final char NON_BREAKING_SPACE = 160;

	private static DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private static DataFormatter formatter = new DataFormatter();

	@NonNull
	private String workSheetName;

	private int rowOffset = 0;
	private int colOffset = 0;
	private Integer lastCellNum;

	@Override
	public String read(Path archivo) throws IOException {
		// http://stackoverflow.com/questions/4929646/how-to-get-an-excel-blank-cell-value-in-apache-poi
		Workbook workbook = createWorkBook(archivo);
		Sheet sheet = getWorkSheet(workbook);

		String result = getData(sheet);

		workbook.close();
		return result;
	}

	private Workbook createWorkBook(Path archivo) throws FileNotFoundException, IOException {
		Workbook workbook = null;
		InputStream in = new FileInputStream(archivo.toFile());

		try {
			workbook = WorkbookFactory.create(in);
		} catch (EncryptedDocumentException e1) {
			throw new RuntimeException(e1);
		} catch (InvalidFormatException e1) {
			throw new RuntimeException(e1);
		} finally {
			if (workbook == null) {
				in.close();
			}
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

	private String getData(Sheet sheet) {
		int numeroColumnasEsperado = getNumeroDeColumnasEsperado(sheet, rowOffset);

		StringBuilder sb = new StringBuilder();
		for (int i = rowOffset; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = colOffset; j < numeroColumnasEsperado; j++) {
				val cell = row.getCell(j);
				val text = getCellText(cell);

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

	private int getNumeroDeColumnasEsperado(Sheet sheet, int rowOffset) {
		int result = 0;

		if (this.getLastCellNum() != null) {
			result = this.getLastCellNum().intValue();
		} else {
			Row row = sheet.getRow(rowOffset);

			if (row != null) {
				result = row.getLastCellNum();
			}
		}

		return result;
	}

	private String getCellText(Cell cell) {
		String text = "";

		if (cell != null) {
			text = formatter.formatCellValue(cell);
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
		}

		if (text.startsWith("T(\"") && text.endsWith("\")")) {
			if (text.equals("T(\"\")")) {
				text = "";
			} else {
				text = text.substring(3, text.length() - 2);
			}
		}

		text = StringUtils.replaceChars(text, "\t\n", "  ");
		return text;
	}
}
