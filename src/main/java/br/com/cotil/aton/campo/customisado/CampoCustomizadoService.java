package br.com.cotil.aton.campo.customisado;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.campo.padrao.CampoPadraoModel;
import br.com.cotil.aton.campo.padrao.CampoPadraoService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

@Service
public class CampoCustomizadoService {

  private CampoCustomizadoRepository campoCustomizadoRepository;
  private CampoPadraoService campoPadraoService;

  public CampoCustomizadoService(CampoCustomizadoRepository campoCustomizadoRepository,
      CampoPadraoService campoPadraoService) {
    super();
    this.campoCustomizadoRepository = campoCustomizadoRepository;
    this.campoPadraoService = campoPadraoService;
  }

  public Page<CampoCustomizadoModel> getCampoCustomizado(UsuarioModel usuario, Integer id,
      String nome, String descricao, boolean ativo, Integer page, Integer size)
      throws BadRequestException {

    Pageable pageable = Utils.setPageRequestConfig(page, size);

    return campoCustomizadoRepository.findWithFilter(id, nome, descricao, ativo, usuario.getId(),
        pageable);
  }

  public CampoCustomizadoModel postCampoCustomisado(UsuarioModel usuario,
      CampoCustomizadoModel campoCustomizado) throws BadRequestException {

    campoCustomizado.setUsuario(usuario);
    campoCustomizado.setAtivo(true);

    campoCustomizado = padronizar(campoCustomizado);

    return campoCustomizadoRepository.save(campoCustomizado);

  }


  public CampoCustomizadoModel atualizarCampoCustomizado(UsuarioModel usuario,
      CampoCustomizadoModel campoCustomizado) throws BadRequestException, ForbiddenException {

    boolean ativo = true;

    CampoCustomizadoModel campoDoBanco =
        validaSeCampoExiste(campoCustomizado.getId(), usuario.getId());
    validaAtivo(campoDoBanco, ativo);

    campoCustomizado = padronizar(campoCustomizado);

    campoDoBanco.setDescricao(campoCustomizado.getDescricao());
    campoDoBanco.setNome(campoCustomizado.getNome());
    campoDoBanco.setPlaceHolder(campoCustomizado.getPlaceHolder());
    campoDoBanco.setToolTip(campoCustomizado.getToolTip());

    return campoCustomizadoRepository.save(campoDoBanco);
  }


  public CampoCustomizadoModel desativarCampoCustomizado(UsuarioModel usuario, Integer idCampo,
      boolean ativo) throws BadRequestException, ForbiddenException {

    CampoCustomizadoModel campoNoBanco = validaSeCampoExiste(idCampo, usuario.getId());

    validaAtivo(campoNoBanco, ativo);

    campoNoBanco.setAtivo(ativo);

    return campoCustomizadoRepository.save(campoNoBanco);
  }

  // ------ Validações e padronizações ------\\

  public CampoCustomizadoModel validaSeCampoExiste(Integer idCampo, Integer idUsuario)
      throws BadRequestException {
    Optional<CampoCustomizadoModel> campoCustomizadoOptional =
        campoCustomizadoRepository.findByIdAndUsuario(idCampo, idUsuario);
    if (!campoCustomizadoOptional.isPresent())
      throw new BadRequestException("Campo não encontrado");

    return campoCustomizadoOptional.get();
  }

  public CampoCustomizadoModel padronizar(CampoCustomizadoModel campoCustomizado)
      throws BadRequestException {

    CampoPadraoModel CampoPadrao =
        campoPadraoService.validaCampoPadrao(campoCustomizado.getCampoPadrao().getId());

    campoCustomizado.setCampoPadrao(CampoPadrao);

    if (Utils.isNullOrEmpty(campoCustomizado.getDescricao()))
      campoCustomizado.setDescricao(CampoPadrao.getDescricao());

    if (Utils.isNullOrEmpty(campoCustomizado.getNome()))
      campoCustomizado.setNome(CampoPadrao.getNome());

    if (Utils.isNullOrEmpty(campoCustomizado.getPlaceHolder()))
      campoCustomizado.setPlaceHolder(CampoPadrao.getPlaceHolder());

    if (Utils.isNullOrEmpty(campoCustomizado.getToolTip()))
      campoCustomizado.setToolTip(CampoPadrao.getToolTip());

    return campoCustomizado;
  }

  public void validaAtivo(CampoCustomizadoModel campo, boolean ativo) throws BadRequestException {
    if (campo.isAtivo() == ativo)
      throw new BadRequestException("campo está desativado des de " + campo.getDataAlteracao());
  }
}

