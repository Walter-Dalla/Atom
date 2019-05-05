package br.com.cotil.aton.campo.padrao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campo/padrao")
public class CampoPadraoController {

  CampoPadraoService campoPadraoService;

  @Autowired
  public CampoPadraoController(CampoPadraoService campoPadraoService) {
    this.campoPadraoService = campoPadraoService;

  }

  @GetMapping
  public Object getCamposPadrao(
      @RequestParam(value = "idCampo", required = false) Integer idCampo) {



    return campoPadraoService.getCamposPadrao(idCampo);
  }


}
