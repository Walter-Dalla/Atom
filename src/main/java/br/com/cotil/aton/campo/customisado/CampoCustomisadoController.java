package br.com.cotil.aton.campo.customisado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;

@RestController
@RequestMapping("/campo/customizado")
public class CampoCustomisadoController {

  CampoCustomizadoService campoCustomizadoService;

  @Autowired
  public CampoCustomisadoController(CampoCustomizadoService campoCustomizadoService) {
    this.campoCustomizadoService = campoCustomizadoService;

  }

  @GetMapping
  public Object getCampoCustomizado(
      @RequestParam(value = "idCampo", required = false) Integer idCampo) {

    return campoCustomizadoService.getCampoCustomizado(idCampo);
  }

  @PostMapping
  public CampoCustomizadoModel postCampoCustomizado(@RequestBody CampoCustomizadoModel campoCustomizado)
      throws BadRequestException {

    return campoCustomizadoService.postCampoCustomisado(campoCustomizado);
  }

}
