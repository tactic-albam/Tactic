package com.tacticlogistics.integrador.files.clientes.tactic.tms.cumplidos.digitales;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.Assert;

import com.tacticlogistics.integrador.dto.ArchivoDTO;
import com.tacticlogistics.integrador.files.handlers.decorators.Decorator;
import com.tacticlogistics.integrador.files.handlers.decorators.Filter;
import com.tacticlogistics.integrador.model.tms.cumplidos.CumplidoDigital;

import lombok.val;

public class ExtraerCamposCumplidoDigitalDecorator extends Decorator<CumplidoDigital> {

	public ExtraerCamposCumplidoDigitalDecorator() {
		super();
	}

	public ExtraerCamposCumplidoDigitalDecorator(Filter<CumplidoDigital> inner) {
		super(inner);
	}

	@Override
	public ArchivoDTO<CumplidoDigital> transformar(ArchivoDTO<CumplidoDigital> archivoDTO) {
		final val result = super.transformar(archivoDTO);
		Assert.notEmpty(result.getRegistros());

		// @formatter:off
		val registros = result.getRegistros();
		val tipoArchivo = result.getTipoArchivo();
		val configuraciones = tipoArchivo.getConfiguraciones().stream().filter(a -> a.getCodigo().equals("SUBDIRECTORIO_RAIZ")).findFirst();
		val sudirectorio =configuraciones.get().getValor();
		
		StringBuilder sb = new StringBuilder();
		
		for (val registro : registros) {
			String nombreArchivo = registro.getDatos().get(CumplidoDigital.NOMBRE_ARCHIVO);
			Path path = Paths.get(nombreArchivo);
			
			for (int i = 0; i < path.getNameCount(); i++) {
				if (path.getName(i).getFileName().toString().equals(sudirectorio)){
					String clienteNombre = path.getName(i+1).toString();
					String numeroOrden = path.getFileName().toString();
					numeroOrden = FilenameUtils.removeExtension(numeroOrden);
					registro.getDatos().put(CumplidoDigital.CLIENTE_NOMBRE, clienteNombre);
					registro.getDatos().put(CumplidoDigital.NUMERO_ORDEN, numeroOrden);
					sb.append(clienteNombre+"\t"+numeroOrden+"\n");
					break;
				}
			}
		}
		result.setRegistros(registros);
		// @formatter:on

		return result;
	}
}
