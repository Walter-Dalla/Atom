package br.com.cotil.aton.formularios.formularioAcesso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class FormularioAcessoService {

  @Autowired
  private FormularioAcessoRepository formularioAcessoRepository;

  public List<FormularioAcessoModel> listraGruposUsuario(UsuarioModel usuario, Integer idGrupo,
      String nomeGrupo, String nomeFormulario) {

    // List<GrupoUsuarioModel> listaGruposUsuario =
    // grupoUsuarioRepository.findAllByUsuario(usuario.getId());

    return null;
  }

  public List<FormularioAcessoModel> pegaTodosOsAcessoDoFormularioDeAcordoComOGrupo(
      Integer idFormulario, List<Integer> idsGruposList) throws ForbiddenException {
    List<FormularioAcessoModel> formularioAcessoList =
        formularioAcessoRepository.findAllByIdFormularioAndIdsGrupo(idFormulario, idsGruposList);

    if (formularioAcessoList.isEmpty())
      throw new ForbiddenException("Você não possui acesso a esse formulario");

    return formularioAcessoList;
  }

}
