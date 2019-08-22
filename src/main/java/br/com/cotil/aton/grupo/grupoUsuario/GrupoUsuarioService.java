package br.com.cotil.aton.grupo.grupoUsuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.grupo.grupo.GrupoModel;
import br.com.cotil.aton.grupo.grupo.GrupoRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.usuario.usuario.UsuarioService;
import br.com.cotil.aton.util.Utils;

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

  public Page<UsuarioModel> getUsuariosDoGrupo(UsuarioModel usuario, Integer idGrupo, Integer page,
      Integer size) throws BadRequestException, ForbiddenException {

    boolean ativo = true;

    GrupoUsuarioModel grupoUsuario = pegarGrupo(idGrupo, usuario.getId());

    return grupoUsuarioRepository.findAllUsuariosByGrupoId(grupoUsuario.getId(), ativo,
        Utils.setPageRequestConfig(page, size));
  }

  public GrupoUsuarioModel pegarGrupo(Integer idGrupo, Integer idUsuario)
      throws BadRequestException {
    Optional<GrupoUsuarioModel> grupoOptional =
        grupoUsuarioRepository.getGrupousuarioByIdGrupoAndIdUsuario(idGrupo, idUsuario);

    if (!grupoOptional.isPresent())
      throw new BadRequestException(GRUPO_NAO_ENCONTRADO);

    return grupoOptional.get();
  }

  public GrupoUsuarioModel adicionaUsuarioNoGrupo(GrupoUsuarioModel grupoUsuario,
      UsuarioModel usuario) throws BadRequestException, ForbiddenException {

    boolean ativo = true;

    GrupoModel grupo = grupoUsuario.getGrupo();
    UsuarioModel usuarioParceiro = grupoUsuario.getUsuario();

    grupo = getGrupo(grupo.getId(), usuario.getId(), ativo);

    usuarioParceiro = usuarioService.getUsuarioById(usuarioParceiro.getId());


    Optional<GrupoUsuarioModel> grupoUsuarioOptional = grupoUsuarioRepository
        .getGrupousuarioByIdGrupoAndIdUsuario(grupo.getId(), usuarioParceiro.getId());

    if (grupoUsuarioOptional.isPresent() && grupoUsuarioOptional.get().isAtivo())
      throw new ForbiddenException(USUARIO_JA_PRESENTE);

    grupoUsuario.setGrupo(grupo);
    grupoUsuario.setUsuario(usuarioParceiro);
    grupoUsuario.setAtivo(true);
    return grupoUsuarioRepository.save(grupoUsuario);
  }


  public GrupoUsuarioModel deleteGrupoUsuario(Integer idUsuario, Integer idGrupo,
      UsuarioModel usuario) throws BadRequestException, ForbiddenException {
    boolean ativo = true;

    UsuarioModel usuarioParceiro = usuarioService.getUsuarioById(idUsuario);
    GrupoModel grupo = getGrupo(idGrupo, idUsuario, ativo);

    GrupoUsuarioModel grupoUsuario = getGrupoUsuario(usuarioParceiro, grupo);

    grupoUsuario.setAtivo(false);

    return grupoUsuarioRepository.save(grupoUsuario);
  }

  public Page<GrupoModel> getAllGruposDoUsuario(UsuarioModel usuario, Integer page, Integer size)
      throws BadRequestException {

    Page<GrupoModel> grupoUsuarioList = grupoUsuarioRepository
        .findAllGruposByUsuario(usuario.getId(), Utils.setPageRequestConfig(page, size));

    return grupoUsuarioList;
  }

  public GrupoUsuarioModel getGrupoUsuario(UsuarioModel usuario, GrupoModel grupo)
      throws BadRequestException {
    Optional<GrupoUsuarioModel> grupoUsuarioOptional =
        grupoUsuarioRepository.findByGrupoAndUsuario(usuario, grupo);

    if (!grupoUsuarioOptional.isPresent())
      throw new BadRequestException("Usuario não está no grupo");

    return grupoUsuarioOptional.get();
  }

  public List<Integer> getAllIdGruposDoUsuario(UsuarioModel usuario){

    List<Integer> listaIdsGrupos = grupoUsuarioRepository.findAllIdGrupoByUsuario(usuario.getId());

    return listaIdsGrupos;
  }


  public GrupoUsuarioModel adicionarUsuarioEmGrupo(GrupoModel grupo, UsuarioModel usuario) {

    GrupoUsuarioModel grupoUsuario = new GrupoUsuarioModel();

    grupoUsuario.setGrupo(grupo);
    grupoUsuario.setUsuario(usuario);
    grupoUsuario.setAtivo(true);

    return grupoUsuarioRepository.save(grupoUsuario);
  }

  public Page<GrupoModel> getGrupousuarioByIdGrupoAndIdUsuario(Integer idGrupo,
      Integer idUsuario) {

    return grupoUsuarioRepository.getGruposByIdGrupoAndIdUsuario(idGrupo, idUsuario,
        Utils.setPageRequestConfig(0, 1));
  }

  public GrupoModel getGrupo(Integer idGrupo, Integer idUsuario, boolean ativo)
      throws BadRequestException {

    Optional<GrupoUsuarioModel> grupoNoBancoOptional = grupoUsuarioRepository
        .getGrupousuarioByIdGrupoAndIdUsuarioAndAtivo(idGrupo, idUsuario, ativo);

    if (!grupoNoBancoOptional.isPresent())
      throw new BadRequestException("Grupo inexistente");

    return grupoNoBancoOptional.get().getGrupo();

  }
  
  public GrupoModel validarGrupo(GrupoModel grupo, UsuarioModel usuario)
      throws BadRequestException, ForbiddenException {
    
    Optional<GrupoUsuarioModel> grupousuarioOptional =
        grupoUsuarioRepository.getGrupousuarioByIdGrupoAndIdUsuario(grupo.getId(), usuario.getId());

    if (!grupousuarioOptional.isPresent())
      throw new BadRequestException("Grupo inexistente ou voce não faz parte deste grupo");

    return grupousuarioOptional.get().getGrupo();
  }
  
  public List<GrupoUsuarioModel> getAllGruposByUsuario(UsuarioModel usuario) throws BadRequestException {

    List<GrupoUsuarioModel> grupoUsuarioList = grupoUsuarioRepository.findAllByUsuario(usuario.getId());

    if(grupoUsuarioList.isEmpty())
      throw new BadRequestException("Usuario não possui grupo");
    
    
    return grupoUsuarioList;
  }

}
