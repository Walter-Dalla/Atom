package br.com.cotil.aton.formularios.campoFormulario;

import java.util.Optional;

import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.formularios.formulario.FormularioModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

public class CampoFormualarioUtils {
	
	
	public static void validadarFormulario(FormularioModel formulario, UsuarioModel usuario) throws UnauthorizedException {
		if (formulario.getUsuario().getId() != usuario.getId())
			throw new UnauthorizedException("Usuario Não Autorizado");
	}
	
	public static void validadarFormulario(Optional<CampoFormularioModel> formulario) throws UnauthorizedException {

		if (!formulario.isPresent())
			throw new UnauthorizedException("Formulario inexistente");
		
		validadarFormulario(formulario.get().getFormulario());
	}
	
	public static void validadarFormulario(FormularioModel formulario) throws UnauthorizedException {
		if (!formulario.isPublicado())
			throw new UnauthorizedException("Formulario inexistente");
	}
	
}
