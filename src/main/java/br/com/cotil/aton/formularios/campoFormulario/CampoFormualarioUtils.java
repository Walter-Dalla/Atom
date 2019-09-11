package br.com.cotil.aton.formularios.campoFormulario;

import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.formularios.formulario.FormularioModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

public class CampoFormualarioUtils {
	
	
	public static void validadarFormulario(FormularioModel formulario, UsuarioModel usuario) throws UnauthorizedException {
		if (formulario.getUsuario().getId() != usuario.getId())
			throw new UnauthorizedException("Usuario Não Autorizado");
	}
	
}
