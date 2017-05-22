package com.tacticlogistics.integrador.files.handlers.decorators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ErrorArchivoDTO;
import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.dto.RegistroDTO;

import lombok.Getter;
import lombok.val;

@Getter
public class ETLRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<ErrorArchivoDTO> errores;

	public ETLRuntimeException(String message, List<ErrorArchivoDTO> errores) {
		this(message, errores, null);
	}

	public ETLRuntimeException(String message, List<ErrorArchivoDTO> errores, Throwable cause) {
		super(message, cause);
		this.errores = new ArrayList<>();
		this.errores.addAll(errores);
	}
	
	public static <T> void throwException(String message,ArchivoDTO<T> archivo){
		Assert.notNull(archivo);
		Assert.notEmpty(archivo.getRegistros());
		
		final List<ErrorArchivoDTO> errores = new ArrayList<>();

		List<RegistroDTO<T>> registros = archivo.getRegistros();
		
		for (int i = 0; i < registros.size(); i++) {
			val registro = registros.get(i);
			if(registro.getErrores().isEmpty()){
				continue;
			}
			errores.add(new ErrorArchivoDTO(i, registro.getLinea(), registro.getErrores()));
		}
		if(!errores.isEmpty()){
			throw new ETLRuntimeException(message,errores);
		}
	}
}
