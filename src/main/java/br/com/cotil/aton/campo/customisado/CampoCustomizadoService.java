package br.com.cotil.aton.campo.customisado;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.cotil.aton.Utils;
import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.campo.padrao.CampoPadraoModel;
import br.com.cotil.aton.campo.padrao.CampoPadraoRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class CampoCustomizadoService {

  private CampoCustomizadoRepository campoCustomizadoRepository;
  private CampoPadraoRepository campoPadraoRepository;

  public CampoCustomizadoService(CampoCustomizadoRepository campoCustomizadoRepository,
      CampoPadraoRepository campoPadraoRepository) {
    super();
    this.campoCustomizadoRepository = campoCustomizadoRepository;
    this.campoPadraoRepository = campoPadraoRepository;
  }

  public Object getCampoCustomizado(Integer idCampo) {

    if (idCampo == null)
      return campoCustomizadoRepository.findAll();
    return campoCustomizadoRepository.findById(idCampo);
  }

  public CampoCustomizadoModel postCampoCustomisado(CampoCustomizadoModel campoCustomizado)
      throws BadRequestException {

    // TODO pegar usuario da autorização
    campoCustomizado.setUsuario(new UsuarioModel());

    padronizar(campoCustomizado);


    return campoCustomizadoRepository.save(campoCustomizado);
  }

  public CampoCustomizadoModel padronizar(CampoCustomizadoModel campoCustomizado)
      throws BadRequestException {

    Optional<CampoPadraoModel> CampoPadraoOptional =
        campoPadraoRepository.findById(campoCustomizado.getId());
    if (!CampoPadraoOptional.isPresent())
      throw new BadRequestException("Campo padrão inexistente");

    campoCustomizado.setCampoPadrao(CampoPadraoOptional.get());
    CampoPadraoModel CampoPadrao = CampoPadraoOptional.get();

    if (Utils.isNullOrEmpty(campoCustomizado.getDescricao()))
      campoCustomizado.setDescricao(CampoPadrao.getDescricao());

    if (Utils.isNullOrEmpty(campoCustomizado.getNome()))
      campoCustomizado.setDescricao(CampoPadrao.getNome());

    if (Utils.isNullOrEmpty(campoCustomizado.getPlaceHolder()))
      campoCustomizado.setPlaceHolder(CampoPadrao.getPlaceHolder());

    if (Utils.isNullOrEmpty(campoCustomizado.getToolTip()))
      campoCustomizado.setToolTip(CampoPadrao.getToolTip());

    return campoCustomizado;
  }
}

