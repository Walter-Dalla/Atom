package br.com.cotil.aton.formularios.formularioAcesso;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioModel;
import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class FormularioAcessoService {

  private GrupoUsuarioRepository grupoUsuarioRepository;

  public List<FormularioAcesso> listraGruposUsuario(UsuarioModel usuario, Integer idGrupo,
      String nomeGrupo, String nomeFormulario) {

    List<GrupoUsuarioModel> listaGruposUsuario =
        grupoUsuarioRepository.findAllByUsuario(usuario.getId());

    return null;
  }

}
