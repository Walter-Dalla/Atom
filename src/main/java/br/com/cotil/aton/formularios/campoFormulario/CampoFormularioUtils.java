package br.com.cotil.aton.formularios.campoFormulario;

import java.util.List;
import java.util.Optional;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.BaseException;
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.formularios.formulario.FormularioModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

public class CampoFormularioUtils {

	private CampoFormularioRepository campoFormularioRepository;

	public CampoFormularioUtils(CampoFormularioRepository campoFormularioRepository) {
		this.campoFormularioRepository = campoFormularioRepository;
	}

	public static void validadarFormulario(FormularioModel formulario, UsuarioModel usuario) throws UnauthorizedException {
		if (formulario.getUsuario().getId() != usuario.getId())
			throw new UnauthorizedException(CampoFormularioConstants.messageWhenUserOwerIsentTokenUser);
	}
	
	public static void validadarFormulario(Optional<CampoFormularioModel> formulario) throws UnauthorizedException {

		if (!formulario.isPresent())
			throw new UnauthorizedException(CampoFormularioConstants.messageWhenFormDontExist);
		
		validadarFormulario(formulario.get().getFormulario());
	}
	
	public static void validadarFormulario(FormularioModel formulario) throws UnauthorizedException {
		if (!formulario.isPublicado())
			throw new UnauthorizedException(CampoFormularioConstants.messageWhenFormIsentPublish);
	}

	public static void validadarFormulario(FormularioModel formulario, BaseException httpError)
			throws BaseException {
		if (!formulario.isPublicado())
			throw httpError;
	}

	public static void validIfFormIsPublish(FormularioModel formulario, BaseException httpError)
			throws BaseException {
		if (!formulario.isPublicado())
			throw httpError;
	}

	public static void validIfFormIsNotPublish(FormularioModel formulario, BaseException httpError)
			throws BaseException {
		if (formulario.isPublicado())
			throw httpError;
	}


	public List<CampoFormularioModel> findAllByFormulario(Integer id) {
		return campoFormularioRepository.findAllByFormulario(id);
	}

	public CampoFormularioModel validaCampoFormulario(CampoFormularioModel campoFormularioModel)
			throws BadRequestException, UnauthorizedException {

		Optional<CampoFormularioModel> campoFormularioOptional = campoFormularioRepository.findById(campoFormularioModel.getId());

		CampoFormularioUtils.validadarFormulario(campoFormularioOptional);

		return campoFormularioModel;

	}
}
