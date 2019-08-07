package br.com.cotil.aton.campo.padrao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.util.Utils;

@Service
public class CampoPadraoService {

  private CampoPadraoRepository campoPadraoRepository;

  public CampoPadraoService(CampoPadraoRepository campoPadraoRepository) {
    this.campoPadraoRepository = campoPadraoRepository;
  }

  public Page<CampoPadraoModel> getCamposPadrao(Integer id, String nome, String descricao,
      Integer page, Integer size) throws BadRequestException {

    Pageable pageable = Utils.setPageRequestConfig(page, size);

    Page<CampoPadraoModel> campoPadrao =
        campoPadraoRepository.findByIdAndNomeAndDescricao(id, nome, descricao, pageable);

    if (campoPadrao.isEmpty())
      throw new BadRequestException("Campo não encontrado");

    return campoPadrao;
  }

  public CampoPadraoModel validaCampoPadrao(Integer id) throws BadRequestException {
    Optional<CampoPadraoModel> campoPadrao = campoPadraoRepository.findById(id);

    if (!campoPadrao.isPresent())
      throw new BadRequestException("Campo não encontrado");


    return campoPadrao.get();
  }

}
