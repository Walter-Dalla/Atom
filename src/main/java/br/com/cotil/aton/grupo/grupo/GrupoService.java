package br.com.cotil.aton.grupo.grupo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class GrupoService {

  private GrupoRepository grupoRepository;

  private GrupoUsuarioService grupoUsuarioService;


  @Autowired
  public GrupoService(GrupoRepository grupoRepository, GrupoUsuarioService grupoUsuarioService) {
    super();
    this.grupoRepository = grupoRepository;
    this.grupoUsuarioService = grupoUsuarioService;
  }


  public Page<GrupoModel> getGrupos(UsuarioModel usuario, Integer idGrupo, Integer page,
      Integer size) throws BadRequestException {

    Page<GrupoModel> grupoList;

    if (idGrupo == null)
      grupoList = grupoUsuarioService.getAllGruposDoUsuario(usuario, page, size);
    else
      grupoList = grupoUsuarioService.getGrupousuarioByIdGrupoAndIdUsuario(idGrupo, usuario);

    return grupoList;
  }



  public GrupoModel createNewGrupo(UsuarioModel usuario, GrupoModel novoGrupo)
      throws BadRequestException {

    novoGrupo = GrupoUtils.validarGrupo(novoGrupo);

    novoGrupo = GrupoUtils.prepararGrupo(novoGrupo, usuario);

    GrupoModel grupoSalvo = grupoRepository.save(novoGrupo);

    grupoUsuarioService.adicionarUsuarioEmGrupo(grupoSalvo, usuario);

    return grupoSalvo;
  }


  public GrupoModel updateGrupos(GrupoModel grupo, UsuarioModel usuario)
      throws BadRequestException {

    GrupoUtils.validarGrupo(grupo);

    GrupoModel grupoNoBanco = getGrupo(grupo.getId(), usuario.getId(), true);

    grupo = GrupoUtils.matchGrupos(grupoNoBanco, grupo);

    return grupoRepository.save(grupo);
  }


  public GrupoModel deleteGrupo(Integer idGrupo, UsuarioModel usuario) throws BadRequestException {

    GrupoModel grupoNoBanco = getGrupo(idGrupo, usuario.getId(), true);

    grupoNoBanco.setAtivo(false);

    return grupoRepository.save(grupoNoBanco);
  }


  public GrupoModel getGrupo(Integer idGrupo, Integer idUsuario, boolean ativo)
      throws BadRequestException {

    return grupoUsuarioService.getGrupo(idGrupo, idUsuario, ativo);

  }
}
