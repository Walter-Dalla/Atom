package br.com.cotil.aton.grupo.grupoUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.grupo.grupo.GrupoModel;
import br.com.cotil.aton.grupo.grupo.GrupoRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.usuario.usuario.UsuarioService;

@Service
public class GrupoUsuarioService extends GrupoUsuarioConstantes {

  GrupoUsuarioRepository grupoUsuarioRepository;
  UsuarioService usuarioService;
  GrupoRepository grupoRepository;

  @Autowired
  public GrupoUsuarioService(GrupoUsuarioRepository grupoUsuarioRepository,
      UsuarioService usuarioService, GrupoRepository grupoRepository) {
    super();
    this.grupoUsuarioRepository = grupoUsuarioRepository;
    this.usuarioService = usuarioService;
    this.grupoRepository = grupoRepository;
  }


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


  public Optional<GrupoUsuarioModel> getGrupousuarioByIdGrupoAndIdUsuario(Integer idGrupo,
      Integer idUsuario) {
    return grupoUsuarioRepository.getGrupousuarioByIdGrupoAndIdUsuario(idGrupo, idUsuario);
  }


  public List<UsuarioModel> getUsuariosDoGrupo(UsuarioModel usuario, Integer idGrupo)
      throws BadRequestException, ForbiddenException {

    Optional<GrupoUsuarioModel> grupoOptional =
        grupoUsuarioRepository.getGrupousuarioByIdGrupoAndIdUsuario(idGrupo, usuario.getId());

    if (!grupoOptional.isPresent())
      throw new BadRequestException(GRUPO_NAO_ENCONTRADO);

    if (grupoOptional.get().getGrupo().getUsuario().getId() != usuario.getId())
      throw new ForbiddenException(USUARIO_NAO_DONO);

    List<UsuarioModel> listaDeUsuarios = new ArrayList<UsuarioModel>();

    List<GrupoUsuarioModel> listaDeUsuariosNoGrupo =
        grupoUsuarioRepository.findAllByGrupo(grupoOptional.get().getId());

    listaDeUsuariosNoGrupo.forEach(grupo -> {
      listaDeUsuarios.add(grupo.getUsuario());
    });


    return listaDeUsuarios;
  }



  public GrupoUsuarioModel createNewGrupoUsuario(GrupoUsuarioModel grupoUsuario,
      UsuarioModel usuario) throws BadRequestException, ForbiddenException {

    GrupoModel grupo = grupoUsuario.getGrupo();
    UsuarioModel usuarioParceiro = grupoUsuario.getUsuario();


    Optional<GrupoModel> grupoOptional = grupoRepository.findById(grupo.getId());

    if (!grupoOptional.isPresent())
      throw new BadRequestException(GRUPO_NAO_ENCONTRADO);
    grupo = grupoOptional.get();

    if (grupo.getUsuario().getId() != usuario.getId())
      throw new ForbiddenException(USUARIO_NAO_DONO);

    Optional<UsuarioModel> usuarioParceiroOptional =
        usuarioService.getUser(usuarioParceiro.getId());

    if (!grupoOptional.isPresent())
      throw new BadRequestException(USUARIO_NAO_ENCONTRADO);
    usuarioParceiro = usuarioParceiroOptional.get();


    Optional<GrupoUsuarioModel> grupoUsuarioOptional = grupoUsuarioRepository
        .getGrupousuarioByIdGrupoAndIdUsuario(grupo.getId(), usuarioParceiro.getId());
    if (grupoUsuarioOptional.isPresent()) {

      grupoUsuario = grupoUsuarioOptional.get();

      if (grupoUsuario.isAtivo())
        throw new ForbiddenException(USUARIO_JA_PRESENTE);

      grupoUsuario.setAtivo(true);
    }else {

    grupoUsuario.setGrupo(grupo);
    grupoUsuario.setUsuario(usuarioParceiro);
    grupoUsuario.setAtivo(true);
    }
    return grupoUsuarioRepository.save(grupoUsuario);
  }


  public GrupoUsuarioModel deleteGrupoUsuario(Integer idGrupoUsuario, UsuarioModel usuario)
      throws BadRequestException, ForbiddenException {

    Optional<GrupoUsuarioModel> grupoUsuarioOptional =
        grupoUsuarioRepository.findById(idGrupoUsuario);


    if (!grupoUsuarioOptional.isPresent())
      throw new BadRequestException(USUARIO_NAO_ENCONTRADO);

    GrupoUsuarioModel grupoUsuario = grupoUsuarioOptional.get();


    if (grupoUsuario.getGrupo().getUsuario().getId() == usuario.getId())
      throw new ForbiddenException(USUARIO_NAO_DONO);

    grupoUsuario.setAtivo(false);

    return grupoUsuarioRepository.save(grupoUsuario);
  }

}
