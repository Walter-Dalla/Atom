package br.com.cotil.aton.formularios.formularioAcesso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

@Service
public class FormularioAcessoService {

  private FormularioAcessoRepository formularioAcessoRepository;
  private GrupoUsuarioService grupoUsuarioService;
    
  @Autowired
  public FormularioAcessoService(FormularioAcessoRepository formularioAcessoRepository,
      GrupoUsuarioService grupoUsuarioService) {
    super();
    this.formularioAcessoRepository = formularioAcessoRepository;
    this.grupoUsuarioService = grupoUsuarioService;
  }

  public List<FormularioAcessoModel> listraGruposUsuario(UsuarioModel usuario, Integer idGrupo,
      String nomeGrupo, String nomeFormulario) {

    // List<GrupoUsuarioModel> listaGruposUsuario =
    // grupoUsuarioRepository.findAllByUsuario(usuario.getId());

    return null;
  }

  public Page<FormularioAcessoModel> pegaFormulariosDoGrupo(UsuarioModel usuario, Integer page,
      Integer size) throws BadRequestException {

    List<Integer> listaIdsGrupos = grupoUsuarioService.getAllIdGruposDoUsuario(usuario);
    
    //formularioAcessoRepository.findAllByGrupoId(listaIdsGrupos, Utils.setPageRequestConfig(page, size));

    return null;
  }

  public List<FormularioAcessoModel> pegaTodosOsAcessoDoFormularioDeAcordoComOGrupo(Integer idFormulario,
      List<Integer> idsGruposList) throws ForbiddenException {
    List<FormularioAcessoModel> formularioAcessoList =
        formularioAcessoRepository.pegaTodosOsAcessoDoFormularioDeAcordoComOGrupo(idFormulario, idsGruposList);

    if (formularioAcessoList.isEmpty())
      throw new ForbiddenException("Você não possui acesso a esse formulario");

    return formularioAcessoList;
  }

}
