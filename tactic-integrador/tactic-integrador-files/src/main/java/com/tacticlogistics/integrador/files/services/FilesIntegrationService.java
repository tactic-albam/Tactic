package com.tacticlogistics.integrador.files.services;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tacticlogistics.integrador.files.handlers.ArchivoHandler;
import com.tacticlogistics.integrador.dto.ArchivoRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FilesIntegrationService {
	@Value("${etl.directorios.locales}")
	private String[] directoriosLocales;

	@Value("${etl.directorio.entradas}")
	private String subDirectorioDeEntradas;

	@Autowired
	List<ArchivoHandler<?, ?>> handlers;

	private ArchivoHandler<?, ?> rootChain = null;

	public void run() {
		log.debug("Inicio");

		List<Path> paths = getDirectorios();
		
		for (Path path : paths) {
			if(!Files.exists(path)){
				continue;
			}
			
			try (DirectoryStream<Path> stream = getSubDirectorios(path)) {
				for (Path directorio : stream) {
					procesarDirectorio(directorio);
				}
			} catch (IOException | DirectoryIteratorException e) {
				log.error("Ocurrio el siguiente error: {}", e.getMessage());
				log.error("El tipo de excepción es {}", e.getClass().getName());
			}
		}
		
		log.debug("fin");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// getSubDirectorios
	// ----------------------------------------------------------------------------------------------------------------
	
	private List<Path> getDirectorios() {
		List<Path> paths = new ArrayList<>();
		for (String directorio : directoriosLocales) {
			paths.add(Paths.get(directorio));
		}
		return paths;
	}
	
	private DirectoryStream<Path> getSubDirectorios(Path path) throws IOException {
		if (Files.notExists(path)) {
			throw new RuntimeException(String.format("El directorio {} no existe", path.toAbsolutePath()));
		}
		return Files.newDirectoryStream(path, getDirectoriosFilter());
	}

	private Filter<Path> getDirectoriosFilter() {
		return new DirectoryStream.Filter<Path>() {
			public boolean accept(Path path) throws IOException {
				return (Files.isDirectory(path));
			}
		};
	}

	// ----------------------------------------------------------------------------------------------------------------
	// getArchivos
	// ----------------------------------------------------------------------------------------------------------------
	private DirectoryStream<Path> getArchivos(Path path) throws IOException {
		if (Files.notExists(path)) {
			throw new RuntimeException(String.format("El directorio {} no existe", path.toAbsolutePath()));
		}
		return Files.newDirectoryStream(path, getArchivosFilter());
	}

	private Filter<Path> getArchivosFilter() {
		return new DirectoryStream.Filter<Path>() {
			public boolean accept(Path path) throws IOException {
				return (Files.isRegularFile(path));
			}
		};
	}

	// ----------------------------------------------------------------------------------------------------------------
	// getSubDirectorios
	// ----------------------------------------------------------------------------------------------------------------
	private void procesarDirectorio(Path root) {
		log.debug("Procesando directorio {}", root);

		Path entradas = root.resolve(this.subDirectorioDeEntradas);
		if (Files.notExists(entradas)) {
			log.debug("No existe el directorio de archivos de entrada {}", entradas.getFileName());
			return;
		}
		procesarArchivos(entradas, entradas);
		procesarSubdirectorios(entradas, entradas);
	}

	private void procesarArchivos(Path root, Path pathArchivo) {
		try (DirectoryStream<Path> stream = getArchivos(pathArchivo)) {
			ArchivoHandler<?, ?> handler = getRootResponsabilityChain();
			for (Path path : stream) {
				// @formatter:off
				handler.receiveRequest(
						ArchivoRequestDTO.builder()
						.root(root)
						.pathArchivo(path)
						.build()
						); 
				// @formatter:on
			}
		} catch (IOException | DirectoryIteratorException e) {
			log.error("Ocurrio el siguiente error: {}", e.getMessage());
			log.error("El tipo de excepción es {}", e.getClass().getName());
		}
	}

	private void procesarSubdirectorios(Path root, Path path) {
		try (DirectoryStream<Path> stream = getSubDirectorios(path)) {
			for (Path directorio : stream) {
				procesarArchivos(root, directorio);
				procesarSubdirectorios(root, directorio);
			}
		} catch (IOException | DirectoryIteratorException e) {
			log.error("Ocurrio el siguiente error: {}", e.getMessage());
			log.error("El tipo de excepción es {}", e.getClass().getName());
		}
	}

	// -----------------------------------------------------------------------------------------------
	private ArchivoHandler<?, ?> getRootResponsabilityChain() {
		if (rootChain == null) {
			for (int i = 0; i < handlers.size(); i++) {
				if (rootChain == null) {
					rootChain = this.handlers.get(i);
				} else {
					rootChain.setNextHandler(this.handlers.get(i));
				}
			}
		}
		return rootChain;
	}

}