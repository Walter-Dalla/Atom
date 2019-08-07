package br.com.cotil.aton.campo.campoGrupo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoService;
import br.com.cotil.aton.grupo.grupo.GrupoModel;
import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioRepository;
import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

@Service
public class CampoGrupoService {


  private CampoGrupoRepository campoGrupoRepository;
  private GrupoUsuarioRepository grupoUsuarioRepository;
  private CampoCustomizadoService campoCustomizadoService;

  private GrupoUsuarioService grupoUsuarioService;

  @Autowired
  public CampoGrupoService(CampoGrupoRepository campoGrupoRepository,
      GrupoUsuarioRepository grupoUsuarioRepository, GrupoUsuarioService grupoUsuarioService,
      CampoCustomizadoService campoCustomizadoService) {
    this.campoGrupoRepository = campoGrupoRepository;
    this.grupoUsuarioRepository = grupoUsuarioRepository;
    this.grupoUsuarioService = grupoUsuarioService;
    this.campoCustomizadoService = campoCustomizadoService;
  }

  public Page<CampoGrupoModel> getCamposDosGruposDoUsuario(UsuarioModel usuario, Integer idGrupo,
      String nomeGrupo, String descricaoGrupo, Integer idCampo, String nomeCampo,
      String descricaoCampo, boolean ativo, Integer page, Integer size) {

    List<Integer> listaDeIdDosGruposDoUsuario =
        grupoUsuarioRepository.findAllIdGrupoByUsuario(usuario.getId());

    return campoGrupoRepository
        .findByIdCampoAndNomeCampoAndDescricaoCampoAndIdGrupoAndNomeGrupoAndDescricaoGrupo(idCampo,
            nomeCampo, descricaoCampo, idGrupo, nomeGrupo, descricaoGrupo,
            listaDeIdDosGruposDoUsuario, ativo, Utils.setPageRequestConfig(page, size));

  }

  public CampoGrupoModel autorizarCampoParaUmGrupo(UsuarioModel usuario, CampoGrupoModel campoGrupo)
      throws BadRequestException, ForbiddenException {

    CampoCustomizadoModel campo =
        campoCustomizadoService.validaSeCampoExiste(campoGrupo.getCampo().getId(), usuario.getId());
    GrupoModel grupo = grupoUsuarioService.validarGrupo(campoGrupo.getGrupo(), usuario);

    campoGrupo.setCampo(campo);
    campoGrupo.setGrupo(grupo);
    // campoGrupo.setNivelPermissao(nivelPermissao);
    campoGrupo.setAtivo(true);
    return campoGrupoRepository.save(campoGrupo);
  }

  public CampoGrupoModel desativarCampoParaUmGrupo(UsuarioModel usuario, Integer idCampoGrupo,
      boolean ativo) throws BadRequestException, ForbiddenException {

    CampoGrupoModel campoGrupoBanco = pegarCampoGrupoNoBanco(idCampoGrupo, usuario.getId());

    campoGrupoBanco.setAtivo(ativo);

    return campoGrupoRepository.save(campoGrupoBanco);
  }

  private CampoGrupoModel pegarCampoGrupoNoBanco(Integer idCampoGrupo, Integer idUsuario)
      throws BadRequestException {

    Optional<CampoGrupoModel> campoGrupo =
        campoGrupoRepository.findByIdAndUsuario(idCampoGrupo, idUsuario);

    if (!campoGrupo.isPresent())
      throw new BadRequestException("Grupo inexistente");

    return campoGrupo.get();
  }

  public List<CampoGrupoModel> validaAutorizacaoDoCampoParaGrupo(Integer idCampo,
      List<Integer> idsGruposList) throws ForbiddenException {
    List<CampoGrupoModel> campoGrupoModelList =
        campoGrupoRepository.findByIdCampoAndIdsGrupo(idCampo, idsGruposList);
    if (campoGrupoModelList.isEmpty())
      throw new ForbiddenException("Você não possui acesso a esse formulario");
    return campoGrupoModelList;
  }
}

