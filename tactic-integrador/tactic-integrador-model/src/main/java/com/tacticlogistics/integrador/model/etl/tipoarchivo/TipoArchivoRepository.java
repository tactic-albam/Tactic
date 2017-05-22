package com.tacticlogistics.integrador.model.etl.tipoarchivo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoArchivoRepository extends JpaRepository<TipoArchivo, Long> {

	TipoArchivo findOneByCodigo(String codigo);
	
}
