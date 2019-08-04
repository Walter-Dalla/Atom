package br.com.cotil.aton.grupo.grupo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioModel;
import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

@Service
public class GrupoService {

  GrupoRepository grupoRepository;

  GrupoUsuarioService grupoUsuarioService;


  @Autowired
  public GrupoService(GrupoRepository grupoRepository, GrupoUsuarioService grupoUsuarioService) {
    super();
    this.grupoRepository = grupoRepository;
    this.grupoUsuarioService = grupoUsuarioService;
  }


  public Page<GrupoModel> getGrupos(UsuarioModel usuario, Integer idGrupo, Integer page, Integer size)
      throws BadRequestException {
    
    Page<GrupoModel> grupoList;

    if (idGrupo == null)
      grupoList = grupoUsuarioService.getAllGruposDoUsuario(usuario, page, size);
    else
      grupoList = grupoUsuarioService.getGrupousuarioByIdGrupoAndIdUsuario(idGrupo, usuario);

    return grupoList;
  }



  public GrupoModel createNewGrupo(UsuarioModel usuario, GrupoModel novoGrupo)
      throws BadRequestException {

    novoGrupo = validarGrupo(novoGrupo);

    novoGrupo.setAtivo(true);

    novoGrupo.setUsuario(usuario);

    GrupoModel grupoSalvo = grupoRepository.save(novoGrupo);

    grupoUsuarioService.adicionarUsuarioEmGrupo(grupoSalvo, usuario);

    return grupoSalvo;
  }



  public GrupoModel updateGrupos(GrupoModel grupo, UsuarioModel usuario)
      throws BadRequestException {

    Optional<GrupoUsuarioModel> grupoNoBancoOptional =
        grupoUsuarioService.getGrupousuarioByIdGrupoAndIdUsuario(grupo.getId(), usuario.getId());

    if (!grupoNoBancoOptional.isPresent())
      throw new BadRequestException("Grupo inexistente");

    GrupoModel grupoNoBanco = grupoNoBancoOptional.get().getGrupo();

    validarGrupo(grupo);

    grupoNoBanco.setNome(grupo.getNome());

    return grupoRepository.save(grupoNoBanco);
  }

  public GrupoModel deleteGrupo(Integer idGrupo, UsuarioModel usuario) throws BadRequestException {
    Optional<GrupoUsuarioModel> grupoNoBancoOptional =
        grupoUsuarioService.getGrupousuarioByIdGrupoAndIdUsuario(idGrupo, usuario.getId());

    if (!grupoNoBancoOptional.isPresent())
      throw new BadRequestException("Grupo inexistente");

    GrupoModel grupo = grupoNoBancoOptional.get().getGrupo();
    grupo.setAtivo(false);
    return grupoRepository.save(grupo);
  }

  private GrupoModel validarGrupo(GrupoModel grupo) throws BadRequestException {

    if (Utils.isNullOrEmpty(grupo.getNome()))
      throw new BadRequestException("O Grupo necessista ter um nome");

    return grupo;
  }
}
