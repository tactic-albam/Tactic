package com.tacticlogistics.integrador.etl.handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;

import com.tacticlogistics.core.patterns.AbstractHandler;
import com.tacticlogistics.integrador.etl.readers.Reader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ArchivoHandler extends AbstractHandler<ArchivoRequest> {
	protected static final Pattern PATTERN_TXT = Pattern.compile("(?i:.*\\.(txt|rpt|csv))");
	protected static final Pattern PATTERN_XLS = Pattern.compile("(?i:.*\\.(xlsx|xls))");

	protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");

	@Value("${etl.directorio.errores}")
	private String subDirectorioDeErrores;

	@Value("${etl.directorio.procesados}")
	private String subDirectorioDeProcesados;

	@Value("${etl.directorio.salidas}")
	private String subDirectorioDeSalidas;

	abstract protected Reader getReader();

	// ----------------------------------------------------------------------------------------------------------------
	// canHandleRequest
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected boolean canHandleRequest(ArchivoRequest request) {
		if (request == null) {
			return false;
		}
		if (this.getReader() == null) {
			return false;
		}
		if (this.getDirectorioRelativo() == null) {
			return false;
		}
		if (this.getFileNamePattern() == null) {
			return false;
		}
		if (!checkCliente(request)) {
			return false;
		}
		if (!checkDirectorioRelativo(request)) {
			return false;
		}
		if (!checkNombreArchivo(request)) {
			return false;
		}

		return true;
	}

	abstract protected Path getCliente();
	
	abstract protected Path getDirectorioRelativo();

	abstract protected Pattern getFileNamePattern();

	private boolean checkCliente(ArchivoRequest request) {
		return this.getCliente().equals(request.getCliente());
	}

	private boolean checkDirectorioRelativo(ArchivoRequest request) {
		return this.getDirectorioRelativo().equals(request.getDirectorioRelativo());
	}

	private boolean checkNombreArchivo(ArchivoRequest request) {
		// @formatter:off
		return this.getFileNamePattern()
				.matcher(request.getArchivo().getFileName().toString())
				.matches();
		// @formatter:on
	}

	// ----------------------------------------------------------------------------------------------------------------
	// handleRequest
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected void handleRequest(ArchivoRequest request) {
		log.debug("Procesando el archivo {}", request);
		
		try {
			String data = this.getReader().read(request.getArchivo());
			log.debug("Contenido del archivo es :{}",data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ArchivoDTO<?> archivoDTO = null;
		// try {
		// archivoDTO = getFileExtractor().extract(archivo);
		// getFileLoader().save(archivoDTO);
		//
		// if (!archivoDTO.hasError()) {
		// backup(archivo);
		// } else {
		// error(archivo);
		// }
		//
		// } catch (RuntimeException e) {
		// fatal(archivo, e);
		// }
	}

	// -----------------------------------------------------------------------------------------------
	protected void backup(Path archivo) {
		log.info("backup {}", archivo.toFile().getAbsolutePath());

		try {
			move(archivo, subDirectorioDeProcesados);
		} catch (IOException | RuntimeException e) {
			fatal(archivo, e);
		}
	}

	protected void error(Path archivo) {
		log.error("error {}", archivo.toFile().getAbsolutePath());

		try {
			move(archivo, subDirectorioDeErrores);
		} catch (IOException | RuntimeException e) {
			fatal(archivo, e);
		}
	}

	protected void fatal(Path archivo, Throwable t) {
		log.error("fatal {} {}", archivo.toFile().getAbsolutePath(), t.getClass().getName());
		t.printStackTrace();

		String fecha = dateTimeFormatter.format(LocalDateTime.now());
		String archivoDestino = String.format("%s-%s-%s.FATAL", fecha, t.getClass().getName(), archivo.getFileName());

		try {
			Files.move(archivo, archivo.resolveSibling(archivoDestino));
		} catch (IOException | RuntimeException e) {
			e.printStackTrace();
		}
	}

	protected void move(Path origen, String subDirectorioDestino) throws IOException {
		Path destino = resolveArchivoDestino(origen, subDirectorioDestino);

		if (Files.notExists(destino.getParent())) {
			Files.createDirectories(destino.getParent());
		}

		Files.move(origen, destino);
	}

	protected Path resolveArchivoDestino(Path archivoOrigen, String subDirectorioDestino) {
		String fecha = dateTimeFormatter.format(LocalDateTime.now());

		Path directorioDestino = resolveDirectorioDestino(archivoOrigen, subDirectorioDestino, fecha);
		Path archivoDestino = directorioDestino.resolve(String.format("%s-%s", fecha, archivoOrigen.getFileName()));

		return archivoDestino;
	}

	protected Path resolveDirectorioDestino(Path archivo, String subDirectorioDestino, String fecha) {
		// String dirMM = fecha.substring(0, 6);
		// String dirDD = fecha.substring(0, 8);
		//
		// Path raiz = Paths.get(directorioRaiz);
		// Path relativo = raiz.relativize(archivo).getParent();
		// Path directorioDestino = raiz.resolve(relativo.subpath(0,
		// 1)).resolve(subDirectorioDestino);
		//
		// if (relativo.getNameCount() > 2) {
		// directorioDestino = directorioDestino.resolve(relativo.subpath(2,
		// relativo.getNameCount()));
		// }
		// directorioDestino = directorioDestino.resolve(dirMM).resolve(dirDD);
		// return directorioDestino;
		return null;
	}

	// -----------------------------------------------------------------------------------------------

	// abstract protected FileExtractor<?> getFileExtractor();

	// abstract protected FileLoader getFileLoader();
}