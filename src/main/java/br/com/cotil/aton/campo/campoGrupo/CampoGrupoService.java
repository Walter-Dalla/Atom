package br.com.cotil.aton.campo.campoGrupo;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoRepository;
import br.com.cotil.aton.grupo.grupo.GrupoModel;
import br.com.cotil.aton.grupo.grupo.GrupoRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class CampoGrupoService {


  private CampoGrupoRepository campoGrupoRepository;
  private GrupoRepository grupoRepository;
  private CampoCustomizadoRepository campoCustomizadoRepository;

  public CampoGrupoService(CampoGrupoRepository campoGrupoRepository) {
    super();
    this.campoGrupoRepository = campoGrupoRepository;
  }

  public List<CampoCustomizadoModel> getCamposDosGruposDoUsuario(UsuarioModel usuario, Integer idGrupo, String nomeGrupo, String descricaoGrupo, Integer idCampo, String nomeCampo, String descricaoCampo) {

//    Pageable pageable = PageRequest.of(0, 10);
//    
//    campoGrupoRepository.findByIdCampoAndNomeCampoAndDescricaoCampoAndIdGrupoAndNomeGrupoAndDescricaoGrupo(
//        idCampo, nomeCampo, descricaoCampo, idGrupo, nomeGrupo, descricaoGrupo, pageable);


    return null;
  }

  public CampoGrupoModel createConexaoEntreCampoEGrupo(UsuarioModel usuario,
      CampoCustomizadoModel campo, GrupoModel grupo)
      throws BadRequestException, ForbiddenException {

    grupo = validarGrupo(grupo, usuario);
    campo = validarCampo(campo, usuario);

    CampoGrupoModel campoGrupo = new CampoGrupoModel();

    campoGrupo.setCampoCustomizado(campo);
    campoGrupo.setGrupo(grupo);
    // campoGrupo.setNivelPermissao(nivelPermissao);
    campoGrupo.setAtivo(true);
    return campoGrupoRepository.save(campoGrupo);
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

    Optional<GrupoModel> grupoOptional = grupoRepository.findById(grupo.getId());

    if (!grupoOptional.isPresent())
      throw new BadRequestException("Grupo inexistente");

    if (grupoOptional.get().getUsuario().getId() != usuario.getId())
      throw new ForbiddenException("Você não tem permissao para o grupo");

    return grupoOptional.get();
  }



}

