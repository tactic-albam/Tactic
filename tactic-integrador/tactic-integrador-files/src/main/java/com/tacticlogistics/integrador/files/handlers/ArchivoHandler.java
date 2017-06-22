package com.tacticlogistics.integrador.files.handlers;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.tacticlogistics.core.patterns.AbstractHandler;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.ETLRuntimeException;
import com.tacticlogistics.integrador.files.handlers.readers.Reader;
import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.ArchivoRequestDTO;
import com.tacticlogistics.integrador.services.ArchivosService;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.TipoArchivo;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.TipoArchivoRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Getter(AccessLevel.PROTECTED)
@Slf4j
public abstract class ArchivoHandler<T, ID extends Serializable> extends AbstractHandler<ArchivoRequestDTO> {
	protected static final Pattern PATTERN_TXT = Pattern.compile("(?i:.*\\.(txt|rpt|csv))");

	protected static final Pattern PATTERN_XLS = Pattern.compile("(?i:.*\\.(xlsx|xls))");

	protected static final Pattern PATTERN_XML = Pattern.compile("(?i:.*\\.(xml))");

	protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");

	protected DateTimeFormatter getDateTimeFormatter() {
		return dateTimeFormatter;
	}

	@Value("${etl.directorio.errores}")
	private String subDirectorioDeErrores;

	@Value("${etl.directorio.procesados}")
	private String subDirectorioDeProcesados;

	@Value("${etl.directorio.salidas}")
	private String subDirectorioDeSalidas;

	@Autowired
	private TipoArchivoRepository tipoArchivoRepository;

	@Autowired
	private ArchivosService archivosService;

	// ----------------------------------------------------------------------------------------------------------------
	//
	// ----------------------------------------------------------------------------------------------------------------
	abstract protected Reader getReader();

	abstract protected String getCodigoTipoArchivo();
	
	abstract protected String getClienteCodigo();

	protected Path getPathCliente() {
		Path result = Paths.get(getClienteCodigo());
		return result;
	}

	abstract protected String getDirectorioRelativo();

	protected Path getPathDirectorioRelativo() {
		Path result = Paths.get(getDirectorioRelativo());
		return result;
	}
	
	abstract protected Pattern getFileNamePattern();

	abstract protected Decorator<T> getTransformador();

	abstract protected JpaRepository<T, ID> getRepository();

	// ----------------------------------------------------------------------------------------------------------------
	// canHandleRequest
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected boolean canHandleRequest(ArchivoRequestDTO request) {
		if (request == null) {
			return false;
		}
		if (this.getReader() == null) {
			return false;
		}
		if (this.getPathDirectorioRelativo() == null) {
			return false;
		}
		if (this.getFileNamePattern() == null) {
			return false;
		}
		if (!checkCliente(request)) {
			return false;
		}
		if (!checkSubDirectorioRelativo(request)) {
			return false;
		}
		if (!checkNombreArchivo(request)) {
			return false;
		}

		return true;
	}

	protected boolean checkCliente(ArchivoRequestDTO request) {
		return this.getPathCliente().equals(request.getCliente());
	}

	protected boolean checkSubDirectorioRelativo(ArchivoRequestDTO request) {
		return this.getPathDirectorioRelativo().equals(request.getSubDirectorioRelativo());
	}

	protected boolean checkNombreArchivo(ArchivoRequestDTO request) {
		// @formatter:off
		return this.getFileNamePattern()
				.matcher(request.getPathArchivo().getFileName().toString())
				.matches();
		// @formatter:on
	}

	// ----------------------------------------------------------------------------------------------------------------
	// handleRequest
	// ----------------------------------------------------------------------------------------------------------------
	@Override
	protected void handleRequest(ArchivoRequestDTO request) {
		Assert.notNull(request);
		Path pathArchivo = request.getPathArchivo();
		Assert.notNull(pathArchivo);
		TipoArchivo tipoArchivo = tipoArchivoRepository.findOneByCodigo(getCodigoTipoArchivo());
		Assert.notNull(tipoArchivo);

		log.debug("Procesando el archivo {} de tipo {}", pathArchivo, tipoArchivo.getCodigo());

		boolean error = false;
		ArchivoDTO<T> archivoDTO = null;
		try {
			archivoDTO = archivosService.<T> crearArchivo(pathArchivo, tipoArchivo);
			archivoDTO = cargar(transformar(extraer(request,archivoDTO)));

			archivosService.marcarValidoPorEstructura(archivoDTO.getArchivo());
		} catch (ETLRuntimeException e) {
			error = true;
			archivosService.marcarNoValidoPorEstructura(archivoDTO.getArchivo(), e.getErrores());
		} catch (IOException | RuntimeException e) {
			error = true;
			archivosService.marcarNoValidoPorExcepcion(archivoDTO.getArchivo(), e);
		} finally {
			if (!error) {
				backupProcesados(request);
			} else {
				backupErrores(request);
			}
		}
	}

	protected ArchivoDTO<T> extraer(ArchivoRequestDTO request,ArchivoDTO<T> archivoDTO) throws IOException {
		String datos = this.getReader().read(archivoDTO.getPathArchivo());

		dump(request,datos);

		archivoDTO.setDatos(datos);
		return archivoDTO;
	}

	protected ArchivoDTO<T> transformar(ArchivoDTO<T> archivoDTO) {
		return this.getTransformador().transformar(archivoDTO);
	}

	@Transactional
	protected ArchivoDTO<T> cargar(ArchivoDTO<T> archivoDTO) {
		// @formatter:off
		val entidades = archivoDTO
				.getRegistros()
				.stream().map(a -> a.getEntidad())
				.collect(Collectors.toList());
		// @formatter:on

		getRepository().save(entidades);

		return archivoDTO;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// BACKUP
	// ----------------------------------------------------------------------------------------------------------------
	protected void backupProcesados(ArchivoRequestDTO request) {
		val archivo = request.getPathArchivo();
		String subDirectorio = this.getSubDirectorioDeProcesados();

		log.info("Realizando copia de seguridad del archivo {} en el subdirectorio {}", archivo, subDirectorio);

		try {
			backup(request, subDirectorio);
		} catch (IOException | RuntimeException e) {
			fatal(request, subDirectorio, e);
		}
	}

	protected void backupErrores(ArchivoRequestDTO request) {
		val archivo = request.getPathArchivo();
		String subDirectorio = this.getSubDirectorioDeErrores();
		log.info("Realizando copia de seguridad del archivo {} en el subdirectorio {}", archivo, subDirectorio);

		try {
			backup(request, subDirectorio);
		} catch (IOException | RuntimeException e) {
			fatal(request, subDirectorio, e);
		}
	}

	protected void dump(ArchivoRequestDTO request, String datos) {
		val archivo = request.getPathArchivo();
		String subDirectorio = this.getSubDirectorioDeSalidas();
		log.info("Realizando dump del archivo {} en el subdirectorio {}", archivo, subDirectorio);

		Path destino = getPathDestino(request, subDirectorio);
		try {
			crearDirectorioSiNoExiste(destino.getParent());
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(destino.toString() + ".TXT"))) {
				bw.write(datos);
			} catch (IOException | RuntimeException e) {
				fatal(request, subDirectorio, e);
			}
		} catch (IOException | RuntimeException e) {
			fatal(request, subDirectorio, e);
		}
	}

	protected void backup(ArchivoRequestDTO request, String subDirectorioDestino) throws IOException {
		Path origen = request.getPathArchivo();
		Path destino = getPathDestino(request, subDirectorioDestino);

		crearDirectorioSiNoExiste(destino.getParent());
		Files.move(origen, destino);
	}

	protected Path getPathDestino(ArchivoRequestDTO request, String subDirectorioDestino) {
		LocalDateTime fechaActualDelSistema = LocalDateTime.now();
		// @formatter:off
		Path result = request
				.getRoot()
				.getParent()
				.resolve(subDirectorioDestino)
				.resolve(this.getPathDirectorioRelativo())
				.resolve(this.getSubdirectorioBackup(request, fechaActualDelSistema))
				.resolve(this.getNombreArchivoBackup(request, fechaActualDelSistema));
		// @formatter:on

		return result;
	}

	protected Path getSubdirectorioBackup(ArchivoRequestDTO request, LocalDateTime fechaActualDelSistema) {
		String value = fechaActualDelSistema.format(getDateTimeFormatter());
		Path result = Paths.get(value.substring(0, 6)).resolve(value.substring(0, 8));
		return result;
	}

	protected String getNombreArchivoBackup(ArchivoRequestDTO request, LocalDateTime fechaActualDelSistema) {
		String value = fechaActualDelSistema.format(getDateTimeFormatter());
		String result = String.format("%s-%s", value, request.getPathArchivo().getFileName());
		return result;
	}

	protected void crearDirectorioSiNoExiste(final Path path) throws IOException {
		if (Files.notExists(path)) {
			log.info("Creando directorio {}", path);
			Files.createDirectories(path);
		}
	}

	// ----------------------------------------------------------------------------------------------------------------
	// FATAL
	// ----------------------------------------------------------------------------------------------------------------
	protected void fatal(ArchivoRequestDTO request, String subDirectorio, Throwable t) {
		val archivo = request.getPathArchivo();
		val fechaActualDelSistema = LocalDateTime.now();

		log.error(
				"Ocurrio el siguiente error al intentar realizar la copia de seguridad del archivo {} en el directorio {}",
				archivo.toString(), subDirectorio, t.getClass().getName(), t);

		String value = fechaActualDelSistema.format(getDateTimeFormatter());
		String archivoError = String.format("%s-%s-%s.error", value, t.getClass().getName(), archivo.getFileName());

		try {
			Path pathError = archivo.resolveSibling(archivoError);
			Files.move(request.getPathArchivo(), pathError, REPLACE_EXISTING);
		} catch (IOException | RuntimeException e) {
			String mensaje = "Ocurrio el siguiente error al intentar renombrar el archivo {} al nombre {}";
			log.error(mensaje, archivo.toString(), archivoError, e);
		}
	}
}