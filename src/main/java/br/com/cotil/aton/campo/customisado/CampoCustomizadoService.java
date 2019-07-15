package br.com.cotil.aton.campo.customisado;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.campo.padrao.CampoPadraoModel;
import br.com.cotil.aton.campo.padrao.CampoPadraoRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

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

  public List<CampoCustomizadoModel> getCampoCustomizado(UsuarioModel usuario, Integer id,
      String nome, String descricao) throws BadRequestException {

    if (id == null && Utils.isNullOrEmpty(nome) && Utils.isNullOrEmpty(descricao))
      return campoCustomizadoRepository.findAllByUsuario(usuario);

    Optional<CampoCustomizadoModel> campoCustomizadoOptional =
        campoCustomizadoRepository.findByIdAndNomeAndDescricao(id, nome, descricao);

    if (!campoCustomizadoOptional.isPresent())
      throw new BadRequestException("Campo não encontrado");

    List<CampoCustomizadoModel> listaDeCampoPadrao = new ArrayList<CampoCustomizadoModel>();

    listaDeCampoPadrao.add(campoCustomizadoOptional.get());

    return listaDeCampoPadrao;
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

    CampoCustomizadoModel campoDoBanco = validaSeCampoExiste(campoCustomizado.getId());

    validaIsDono(campoDoBanco, usuario);

    campoCustomizado = padronizar(campoCustomizado);

    campoDoBanco.setDescricao(campoCustomizado.getDescricao());
    campoDoBanco.setNome(campoCustomizado.getNome());
    campoDoBanco.setPlaceHolder(campoCustomizado.getPlaceHolder());
    campoDoBanco.setToolTip(campoCustomizado.getToolTip());
    campoDoBanco.setAtivo(campoCustomizado.isAtivo());
    return campoCustomizadoRepository.save(campoDoBanco);
  }


  public CampoCustomizadoModel desativarCampoCustomizado(UsuarioModel usuario, Integer idCampo)
      throws BadRequestException, ForbiddenException {


    CampoCustomizadoModel campoNoBanco = validaSeCampoExiste(idCampo);

    validaIsDono(campoNoBanco, usuario);

    if(!campoNoBanco.isAtivo())
      throw new BadRequestException("Este campo já está inativo");
    
    campoNoBanco.setAtivo(false);
    
    return campoCustomizadoRepository.save(campoNoBanco);
  }

  // ------ Validações e padronizações ------\\

  public CampoCustomizadoModel validaSeCampoExiste(Integer idCampo) throws BadRequestException {
    Optional<CampoCustomizadoModel> campoCustomizadoOptional =
        campoCustomizadoRepository.findById(idCampo);
    if (!campoCustomizadoOptional.isPresent())
      throw new BadRequestException("Campo não encontrado");

    return campoCustomizadoOptional.get();
  }

  public void validaIsDono(CampoCustomizadoModel campo, UsuarioModel usuario)
      throws ForbiddenException {
    if (campo.getUsuario().getId() != usuario.getId())
      throw new ForbiddenException("Sem autorização para atualizar esse campo");
  }

  private CampoPadraoModel validaCampoPadrao(CampoCustomizadoModel campoCustomizado)
      throws BadRequestException {

    Optional<CampoPadraoModel> CampoPadraoOptional =
        campoPadraoRepository.findById(campoCustomizado.getCampoPadrao().getId());

    if (!CampoPadraoOptional.isPresent())
      throw new BadRequestException("Campo padrão inexistente");

    return CampoPadraoOptional.get();
  }

  public CampoCustomizadoModel padronizar(CampoCustomizadoModel campoCustomizado)
      throws BadRequestException {

    CampoPadraoModel CampoPadrao = validaCampoPadrao(campoCustomizado);

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
}

