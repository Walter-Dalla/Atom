package br.com.cotil.aton.campo.campoGrupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoRepository;
import br.com.cotil.aton.grupo.grupo.GrupoModel;
import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioModel;
import br.com.cotil.aton.grupo.grupoUsuario.GrupoUsuarioRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

@Service
public class CampoGrupoService {


  private CampoGrupoRepository campoGrupoRepository;
  private GrupoUsuarioRepository grupoUsuarioRepository;
  private CampoCustomizadoRepository campoCustomizadoRepository;

  @Autowired
  public CampoGrupoService(CampoGrupoRepository campoGrupoRepository,
      GrupoUsuarioRepository grupoUsuarioRepository,
      CampoCustomizadoRepository campoCustomizadoRepository) {
    this.campoGrupoRepository = campoGrupoRepository;
    this.grupoUsuarioRepository = grupoUsuarioRepository;
    this.campoCustomizadoRepository = campoCustomizadoRepository;
  }

  public Page<CampoGrupoModel> getCamposDosGruposDoUsuario(UsuarioModel usuario, Integer idGrupo,
      String nomeGrupo, String descricaoGrupo, Integer idCampo, String nomeCampo,
      String descricaoCampo, boolean ativo, Integer page, Integer size) {

    List<GrupoUsuarioModel> listaDeGruposDoUsuario =
        grupoUsuarioRepository.findAllByUsuario(usuario.getId());

    List<Integer> listaDeIdDosGruposDoUsuario = new ArrayList<Integer>();

    listaDeGruposDoUsuario.forEach(GrupoDoUsuario -> {
      listaDeIdDosGruposDoUsuario.add(GrupoDoUsuario.getGrupo().getId());
    });

    return campoGrupoRepository
        .findByIdCampoAndNomeCampoAndDescricaoCampoAndIdGrupoAndNomeGrupoAndDescricaoGrupo(idCampo,
            nomeCampo, descricaoCampo, idGrupo, nomeGrupo, descricaoGrupo,
            listaDeIdDosGruposDoUsuario, ativo, Utils.setPageRequestConfig(page, size));

  }

  public CampoGrupoModel autorizarCampoParaUmGrupo(UsuarioModel usuario, CampoGrupoModel campoGrupo)
      throws BadRequestException, ForbiddenException {

    campoGrupo.setCampo(validarCampo(campoGrupo.getCampo(), usuario));
    campoGrupo.setGrupo(validarGrupo(campoGrupo.getGrupo(), usuario));
    // campoGrupo.setNivelPermissao(nivelPermissao);
    campoGrupo.setAtivo(true);
    return campoGrupoRepository.save(campoGrupo);
  }

  public CampoGrupoModel desativarCampoParaUmGrupo(UsuarioModel usuario, Integer idCampoGrupo)
      throws BadRequestException, ForbiddenException {

    CampoGrupoModel campoGrupoBanco = pegarCampoGrupoNoBanco(idCampoGrupo);

    if (campoGrupoBanco.getCampo().getUsuario().getId() != usuario.getId())
      throw new ForbiddenException("Você não é dono deste campo");

    campoGrupoBanco.setAtivo(false);

    return campoGrupoRepository.save(campoGrupoBanco);
  }

  private CampoGrupoModel pegarCampoGrupoNoBanco(Integer idCampoGrupo) throws BadRequestException {

    Optional<CampoGrupoModel> campoGrupo = campoGrupoRepository.findById(idCampoGrupo);

    if (!campoGrupo.isPresent())
      throw new BadRequestException("Grupo inexistente");

    return campoGrupo.get();
  }

  private CampoCustomizadoModel validarCampo(CampoCustomizadoModel campo, UsuarioModel usuario)
      throws ForbiddenException, BadRequestException {

    Optional<CampoCustomizadoModel> campoOptional =
        campoCustomizadoRepository.findById(campo.getId());

    if (!campoOptional.isPresent())
      throw new BadRequestException("Campo inexistente");

    if (campoOptional.get().getUsuario().getId() != usuario.getId())
      throw new ForbiddenException("Você não tem permissao para o campo");

    return campoOptional.get();
  }

  private GrupoModel validarGrupo(GrupoModel grupo, UsuarioModel usuario)
      throws BadRequestException, ForbiddenException {

    Optional<GrupoUsuarioModel> grupousuarioOptional =
        grupoUsuarioRepository.getGrupousuarioByIdGrupoAndIdUsuario(grupo.getId(), usuario.getId());

    if (!grupousuarioOptional.isPresent())
      throw new BadRequestException("Grupo inexistente ou voce não faz parte deste grupo");

    return grupousuarioOptional.get().getGrupo();
  }

  public List<CampoGrupoModel> validaAutorizacaoDoCampoParaGrupo(Integer idCampo, List<Integer> idsGruposList)
      throws ForbiddenException {
    List<CampoGrupoModel> campoGrupoModelList =
        campoGrupoRepository.findByIdCampoAndIdsGrupo(idCampo, idsGruposList);
    if (campoGrupoModelList.isEmpty())
      throw new ForbiddenException("Você não possui acesso a esse formulario");
    return campoGrupoModelList;
  }



}

