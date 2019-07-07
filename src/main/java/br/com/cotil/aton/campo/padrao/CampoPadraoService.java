package br.com.cotil.aton.campo.padrao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.util.Utils;

@Service
public class CampoPadraoService {

  private CampoPadraoRepository campoPadraoRepository;

  public CampoPadraoService(CampoPadraoRepository campoPadraoRepository) {
    this.campoPadraoRepository = campoPadraoRepository;
  }

  public List<CampoPadraoModel> getCamposPadrao(Integer id, String nome, String descricao)
      throws BadRequestException {
    if (id == null && Utils.isNullOrEmpty(nome) && Utils.isNullOrEmpty(descricao))
      return campoPadraoRepository.findAll();

    Optional<CampoPadraoModel> campoPadraoModelOptional =
        campoPadraoRepository.findByIdAndNomeAndDescricao(id, nome, descricao);
    
    if (!campoPadraoModelOptional.isPresent())
      throw new BadRequestException("Campo não encontrado");

    List<CampoPadraoModel> listaDeCampoPadrao = new ArrayList<CampoPadraoModel>();

    listaDeCampoPadrao.add(campoPadraoModelOptional.get());

    return listaDeCampoPadrao;
  }



}
