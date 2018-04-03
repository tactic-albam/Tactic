package com.tacticlogistics.integrador.db.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tacticlogistics.integrador.db.dto.DbRequestDTO;
import com.tacticlogistics.integrador.db.handlers.DbHandler;
import com.tacticlogistics.integrador.model.etl.archivo.Archivo;
import com.tacticlogistics.integrador.model.etl.archivo.ArchivoRepository;
import com.tacticlogistics.integrador.model.etl.archivo.EstadoArchivoType;
import com.tacticlogistics.integrador.model.etl.tipoarchivo.TipoArchivoRepository;

import lombok.val;

@Service
public class DbIntegrationService {
	@Autowired
	private List<DbHandler> handlers;

	private DbHandler rootChain = null;

	@Autowired
	private ArchivoRepository archivoRepository;

	@Autowired
	private TipoArchivoRepository tipoArchivoRepository;

	public void run() {
		DbHandler handler = getRootResponsabilityChain();

		val archivos =archivoRepository.findByEstado(EstadoArchivoType.VALIDADO);
		// val archivos = new ArrayList<Archivo>();
		// val a = archivoRepository.findOne(146277L);
		// archivos.add(a);
		for (Archivo archivo : archivos) {
			val tipoArchivo = tipoArchivoRepository.findOne(archivo.getIdTipoArchivo());

			DbRequestDTO request = new DbRequestDTO();
			request.setTipoArchivo(tipoArchivo);
			request.setArchivo(archivo);
			handler.receiveRequest(request);
		}
	}

	private DbHandler getRootResponsabilityChain() {
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