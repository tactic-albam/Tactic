package com.tacticlogistics.integrador.model.wms.pf;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntoFormProductoMedidaRepository extends JpaRepository<ProntoFormProductoMedida, Long> {
	List<ProntoFormProductoMedida> findByIdArchivo(Long idArchivo); 
}
