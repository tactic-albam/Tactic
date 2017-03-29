package com.tacticlogistics.integrador.etl.handlers.decorators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.etl.handlers.dto.ArchivoDTO;
import com.tacticlogistics.integrador.etl.handlers.dto.RegistroDTO;

import lombok.Getter;
import lombok.val;

@Getter
public class ETLRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<ETLErrorDTO> errores;

	public ETLRuntimeException(String message, List<ETLErrorDTO> errores) {
		this(message, errores, null);
	}

	public ETLRuntimeException(String message, List<ETLErrorDTO> errores, Throwable cause) {
		super(message, cause);
		this.errores = new ArrayList<>();
		this.errores.addAll(errores);
	}
	
	public static <T> void throwException(String message,ArchivoDTO<T> archivo){
		Assert.notNull(archivo);
		Assert.notEmpty(archivo.getRegistros());
		
		final List<ETLErrorDTO> errores = new ArrayList<>();

		List<RegistroDTO<T>> registros = archivo.getRegistros();
		
		for (int i = 0; i < registros.size(); i++) {
			val registro = registros.get(i);
			if(registro.getErrores().isEmpty()){
				continue;
			}
			errores.add(new ETLErrorDTO(i, registro.getLinea(), registro.getErrores()));
		}
		if(!errores.isEmpty()){
			throw new ETLRuntimeException(message,errores);
		}
	}
}
