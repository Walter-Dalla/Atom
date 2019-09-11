package br.com.cotil.aton.formularios.campoFormulario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.campo.CampoUtils;
import br.com.cotil.aton.campo.campoGrupo.CampoGrupoService;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoService;
import br.com.cotil.aton.formularios.formulario.FormularioModel;
import br.com.cotil.aton.formularios.formulario.FormularioRepository;
import br.com.cotil.aton.formularios.formulario.FormularioService;
import br.com.cotil.aton.formularios.formularioAcesso.FormularioAcessoService;
import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class CampoFormularioService {

	CampoFormularioRepository campoFormularioRepository;
	FormularioRepository formularioRepository;
	FormularioAcessoService formularioAcessoService;
	GrupoUsuarioService grupoUsuarioService;
	CampoCustomizadoService campoCustomizadoService;
	CampoGrupoService campoGrupoService;
	FormularioService formularioService;

	@Autowired
	public CampoFormularioService(CampoFormularioRepository campoFormularioRepository,
			FormularioRepository formularioRepository, FormularioAcessoService formularioAcessoService,
			GrupoUsuarioService grupoUsuarioService, CampoCustomizadoService campoCustomizadoService,
			CampoGrupoService campoGrupoService, FormularioService formularioService) {
		super();
		this.campoFormularioRepository = campoFormularioRepository;
		this.formularioRepository = formularioRepository;
		this.formularioAcessoService = formularioAcessoService;
		this.grupoUsuarioService = grupoUsuarioService;
		this.campoCustomizadoService = campoCustomizadoService;
		this.campoGrupoService = campoGrupoService;
		this.formularioService = formularioService;
	}

	public List<CampoFormularioModel> listaCamposFormularios(UsuarioModel usuario, Integer idFormulario)
			throws BadRequestException, ForbiddenException, UnauthorizedException {

		FormularioModel formulario = formularioService.pegaFormularioDoBanco(idFormulario, usuario);

		CampoFormualarioUtils.validadarFormulario(formulario, usuario);

		return campoFormularioRepository.findAllByFormulario(idFormulario);
	}

	public CampoFormularioModel criaFormulario(UsuarioModel usuario, CampoFormularioModel campoFormularioModel)
			throws BadRequestException, ForbiddenException, UnauthorizedException {

		FormularioModel formulario = formularioService
				.pegaFormularioDoBanco(campoFormularioModel.getFormulario().getId(), usuario);

		campoFormularioModel.setFormulario(formulario);

		CampoCustomizadoModel campo = campoCustomizadoService.getCampoCustomizado(usuario,
				campoFormularioModel.getCampo().getId());

		campoFormularioModel.setCampo(CampoUtils.validaCampo(campo, usuario));

		campoFormularioModel.setAtivo(true);

		return campoFormularioRepository.save(campoFormularioModel);
	}

	public CampoFormularioModel desabilitaFormulario(Integer idCampoFormulario, UsuarioModel usuario)
			throws BadRequestException, ForbiddenException {

		Optional<CampoFormularioModel> campoFormularioOptional = campoFormularioRepository.findById(idCampoFormulario);

		if (!campoFormularioOptional.isPresent())
			throw new BadRequestException("Campo formulario inexistente");

		CampoFormularioModel campoFormulario = campoFormularioOptional.get();

		campoCustomizadoService.validaSeCampoExiste(campoFormulario.campo.getId(), usuario.getId());

		campoFormulario.setAtivo(false);

		return campoFormularioRepository.save(campoFormulario);
	}

}
