package br.com.cotil.aton.formularios.campoFormulario;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.campo.CampoUtils;
import br.com.cotil.aton.campo.campoGrupo.CampoGrupoService;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoRepository;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoService;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoUtils;
import br.com.cotil.aton.formularios.formulario.FormularioModel;
import br.com.cotil.aton.formularios.formulario.FormularioRepository;
import br.com.cotil.aton.formularios.formulario.FormularioUtils;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.OptionalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampoFormularioService {

	private final CampoFormularioUtils campoFormularioUtils;
	private final CampoFormularioRepository campoFormularioRepository;

	private final CampoCustomizadoService campoCustomizadoService;

	private final CampoGrupoService campoGrupoService;
	private final CampoCustomizadoUtils campoCustomizadoUtils;
	private final FormularioUtils formularioUtils;

	@Autowired
	public CampoFormularioService(CampoFormularioRepository campoFormularioRepository,
			FormularioRepository formularioRepository, CampoCustomizadoService campoCustomizadoService,
			CampoGrupoService campoGrupoService,
			  CampoCustomizadoRepository campoCustomizadoRepository) {
		super();
		this.formularioUtils = new FormularioUtils(formularioRepository);
		this.campoFormularioRepository = campoFormularioRepository;
		this.campoCustomizadoService = campoCustomizadoService;
		this.campoGrupoService = campoGrupoService;

		this.campoCustomizadoUtils = new CampoCustomizadoUtils(campoCustomizadoRepository);
		this.campoFormularioUtils = new CampoFormularioUtils(campoFormularioRepository);

	}

	/**
	 * Metodo Get
	 *
	 */
	public List<CampoFormularioModel> listaCamposFormularios(UsuarioModel usuario, Integer idFormulario)
			throws BadRequestException, ForbiddenException, UnauthorizedException {

		FormularioModel formulario = formularioUtils.pegaFormularioDoBanco(idFormulario, usuario);

		CampoFormularioUtils.validadarFormulario(formulario, usuario);

		return campoFormularioRepository.findAllByFormulario(idFormulario);
	}

	/**
	 * Metodo Post
	 *
	 */
	public CampoFormularioModel criaFormulario(UsuarioModel usuario, CampoFormularioModel campoFormularioModel)
			throws BadRequestException, ForbiddenException, UnauthorizedException {

		Integer idFormulario = campoFormularioModel.getFormulario().getId();
		
		FormularioModel formulario = formularioUtils
				.pegaFormularioDoBanco(idFormulario, usuario);

		CampoFormularioUtils.validIfFormIsNotPublish(formulario,
				new BadRequestException(CampoFormularioConstants.messageWhenFormIsPublishedAndAddCampos));
		
		campoFormularioModel.setFormulario(formulario);

		CampoCustomizadoModel campo = campoCustomizadoService.getCampoCustomizado(usuario,
				campoFormularioModel.getCampo().getId());

		CampoUtils.validaCampo(campo, usuario);

		CampoCustomizadoModel campoCustomizadoModel = new CampoCustomizadoModel(campo);
		campoCustomizadoModel.setId(0);
		campoCustomizadoModel.setMarcado(false);
		campoFormularioModel.setAtivo(true);
		campoFormularioModel.setPublicado(formulario.isPublicado());
		campoFormularioModel.setCampo(campoCustomizadoService.postCampoCustomisado(usuario, campoCustomizadoModel));

		return campoFormularioRepository.save(campoFormularioModel);
	}

	/**
	 * Metodo Delete
	 *
	 */
	public CampoFormularioModel desabilitaFormulario(Integer idCampoFormulario, UsuarioModel usuario)
			throws BadRequestException, ForbiddenException {

		Optional<CampoFormularioModel> campoFormularioOptional = campoFormularioRepository.findById(idCampoFormulario);

		OptionalUtils.validIfIsNotPresent(campoFormularioOptional,
				new BadRequestException(CampoFormularioConstants.messageWhenFormDontExist));
		
		CampoFormularioModel campoFormulario = campoFormularioOptional.get();

		campoCustomizadoUtils.validaSeCampoExiste(campoFormulario.getCampo().getId(), usuario.getId());

		if(campoFormulario.isPublicado())
			throw new BadRequestException(CampoFormularioConstants.messageWhenFormIsPublishedAndDeleteCampos);

		campoFormularioRepository.delete(campoFormulario);
		
		return campoFormulario;
	}
	


}
