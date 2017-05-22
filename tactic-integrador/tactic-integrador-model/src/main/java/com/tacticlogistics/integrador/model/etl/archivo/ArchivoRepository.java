package com.tacticlogistics.integrador.model.etl.archivo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
	List<Archivo> findByEstado(EstadoArchivoType estado);
}
