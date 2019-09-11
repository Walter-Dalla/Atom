package br.com.cotil.aton.campo;

import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

public class CampoUtils {
	public static CampoCustomizadoModel validaCampo(CampoCustomizadoModel campo, UsuarioModel usuario) throws UnauthorizedException{

		if(campo.getUsuario().getId() != usuario.getId() )
			throw new UnauthorizedException("Campo não autorizado");
		
		return campo;
	}
}
