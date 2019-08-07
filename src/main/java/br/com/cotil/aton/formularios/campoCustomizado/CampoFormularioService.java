package br.com.cotil.aton.formularios.campoCustomizado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.campo.campoGrupo.CampoGrupoModel;
import br.com.cotil.aton.campo.campoGrupo.CampoGrupoService;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoService;
import br.com.cotil.aton.formularios.formulario.FormularioModel;
import br.com.cotil.aton.formularios.formulario.FormularioRepository;
import br.com.cotil.aton.formularios.formulario.FormularioService;
import br.com.cotil.aton.formularios.formularioAcesso.FormularioAcessoModel;
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


  public List<CampoFormularioModel> listaCamposFormularios(UsuarioModel usuario,
      Integer idFormulario) throws BadRequestException, ForbiddenException {

    FormularioModel formulario = formularioService.pegaFormularioDoBanco(idFormulario, usuario);

    validarGrupoFormulario(formulario, usuario);

    return campoFormularioRepository.findAllByFormulario(idFormulario);
  }


  public CampoFormularioModel criaFormulario(UsuarioModel usuario,
      CampoFormularioModel campoFormularioModel) throws BadRequestException, ForbiddenException {

    FormularioModel formulario =
        formularioService.pegaFormularioDoBanco(campoFormularioModel.getFormulario().getId(), usuario);

    campoFormularioModel.setFormulario(validarGrupoFormulario(formulario, usuario));

    campoFormularioModel.setCampo(validaCampo(campoFormularioModel.getCampo(), usuario));

    campoFormularioModel.setAtivo(true);
    
    return campoFormularioRepository.save(campoFormularioModel);
  }

  public CampoFormularioModel desabilitaFormulario(Integer idCampoFormulario, UsuarioModel usuario)
      throws BadRequestException, ForbiddenException {

    Optional<CampoFormularioModel> campoFormularioOptional =
        campoFormularioRepository.findById(idCampoFormulario);

    if (!campoFormularioOptional.isPresent())
      throw new BadRequestException("Campo formulario inexistente");

    CampoFormularioModel campoFormulario = campoFormularioOptional.get();

    validarGrupoFormulario(campoFormulario.getFormulario(), usuario);
    validaCampo(campoFormulario.getCampo(), usuario);

    campoFormulario.setAtivo(false);

    return campoFormularioRepository.save(campoFormulario);
  }

  /**
   * Valida se o usuario possui aceso ao formulario
   * 
   * @param formulario
   * @param usuario
   * @return
   * @throws BadRequestException
   * @throws ForbiddenException
   */
  private FormularioModel validarGrupoFormulario(FormularioModel formulario, UsuarioModel usuario)
      throws BadRequestException, ForbiddenException {

    List<Integer> idsGruposList = grupoUsuarioService.getAllIdGruposDoUsuario(usuario);

    List<FormularioAcessoModel> formularioAcessoList = formularioAcessoService
        .pegaTodosOsAcessoDoFormularioDeAcordoComOGrupo(formulario.getId(), idsGruposList);

    return formularioAcessoList.get(0).getFormulario();
  }

  /**
   * 
   * Valida se o usuario possui aceso ao campo
   * 
   * @param campo
   * @param usuario
   * @return
   * @throws ForbiddenException
   * @throws BadRequestException
   */
  private CampoCustomizadoModel validaCampo(CampoCustomizadoModel campo, UsuarioModel usuario)
      throws ForbiddenException, BadRequestException {

    campoCustomizadoService.validaSeCampoExiste(campo.getId(), usuario.getId());

    List<Integer> idsGruposList = grupoUsuarioService.getAllIdGruposDoUsuario(usuario);

    List<CampoGrupoModel> campoGrupoModelList =
        campoGrupoService.validaAutorizacaoDoCampoParaGrupo(campo.getId(), idsGruposList);

    return campoGrupoModelList.get(0).getCampo();
  }

}
