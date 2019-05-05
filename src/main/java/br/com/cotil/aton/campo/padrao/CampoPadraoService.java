package br.com.cotil.aton.campo.padrao;

import org.springframework.stereotype.Service;

@Service
public class CampoPadraoService {

  private CampoPadraoRepository campoPadraoRepository;

  public CampoPadraoService(CampoPadraoRepository campoPadraoRepository) {
    this.campoPadraoRepository = campoPadraoRepository;
  }

  public Object getCamposPadrao(Integer idCampo) {

    if(idCampo == null)
      return campoPadraoRepository.findAll();
    return campoPadraoRepository.findById(idCampo);
  }



}
