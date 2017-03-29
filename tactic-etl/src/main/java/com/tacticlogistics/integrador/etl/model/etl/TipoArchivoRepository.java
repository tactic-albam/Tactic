package com.tacticlogistics.integrador.etl.model.etl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoArchivoRepository extends JpaRepository<TipoArchivo, Long> {

	TipoArchivo findOneByCodigo(String codigo);
	
}
