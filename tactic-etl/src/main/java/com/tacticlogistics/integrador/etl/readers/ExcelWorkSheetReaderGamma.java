package com.tacticlogistics.integrador.etl.readers;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
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
public class ExcelWorkSheetReaderGamma implements Reader {
	public static final char NON_BREAKING_SPACE = 160;
	@NonNull
	private String workSheetName;
	
	@Override
	public String read(Path archivo) throws IOException {
		// http://stackoverflow.com/questions/4929646/how-to-get-an-excel-blank-cell-value-in-apache-poi
		Workbook workbook = createWorkBook(archivo);
		Sheet sheet;

		try {
			int index = Integer.valueOf(this.getWorkSheetName());
			sheet = workbook.getSheetAt(index);
		} catch (NumberFormatException e) {
			sheet = workbook.getSheet(this.getWorkSheetName());
		}

		StringBuilder sb = new StringBuilder();

		for (Row row : sheet) {
			for (int cn = 0; cn < row.getLastCellNum(); cn++) {
				// If the cell is missing from the file, generate a blank one
				// (Works by specifying a MissingCellPolicy)
				Cell cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
				// Print the cell for debugging
				String value = cell.toString();
				try {
					if (value.startsWith("T(\"") && value.endsWith("\")")) {
						if (value.equals("T(\"\")")) {
							value = "";
						} else {
							value = value.substring(3, value.length() - 2);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				sb.append(value).append("\t");
			}
			if (sb.length() > 0) {
				sb.setLength(sb.length() - 1);
			}
			sb.append("\n");
		}

		workbook.close();

		String result = sb.toString();
		ArchivoHandler.write(result);
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
}
