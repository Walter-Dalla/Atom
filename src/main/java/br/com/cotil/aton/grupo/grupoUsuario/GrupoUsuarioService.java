package br.com.cotil.aton.grupo.grupoUsuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.grupo.grupo.GrupoModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class GrupoUsuarioService {

  @Autowired
  GrupoUsuarioRepository grupoUsuarioRepository;

  public List<GrupoUsuarioModel> getGruposUsuarios(UsuarioModel usuario)
      throws BadRequestException {


    List<GrupoUsuarioModel> grupoList = grupoUsuarioRepository.findAllByUsuario(usuario.getId());

    return grupoList;
  }


  public GrupoUsuarioModel adicionarUsuarioEmGrupo(GrupoModel grupo, UsuarioModel usuario) {

    GrupoUsuarioModel grupoUsuario = new GrupoUsuarioModel();

    grupoUsuario.setGrupo(grupo);
    grupoUsuario.setUsuario(usuario);
    grupoUsuario.setAtivo(true);
      
    return grupoUsuarioRepository.save(grupoUsuario);
  }


  public List<GrupoUsuarioModel> getUsuariosDoGrupo(GrupoModel grupoModel) {

    return grupoUsuarioRepository.findAllByGrupo(grupoModel.getId());
  }


  public Optional<GrupoUsuarioModel> getGrupousuarioByIdGrupoAndIdUsuario(Integer idGrupo, Integer idUsuario) {
    return grupoUsuarioRepository.getGrupousuarioByIdGrupoAndIdUsuario(idGrupo, idUsuario);
  }

}
