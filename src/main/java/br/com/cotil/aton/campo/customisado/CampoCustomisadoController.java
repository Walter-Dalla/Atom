package br.com.cotil.aton.campo.customisado;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.usuario.token.TokenService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.RequestUtils;

@RestController
@RequestMapping("/campo/customizado")
public class CampoCustomisadoController {

  CampoCustomizadoService campoCustomizadoService;
  TokenService tokenService;

  @Autowired
  public CampoCustomisadoController(CampoCustomizadoService campoCustomizadoService,
      TokenService tokenService) {
    this.campoCustomizadoService = campoCustomizadoService;
    this.tokenService = tokenService;

  }

  @GetMapping
  public Object getCampoCustomizado(HttpServletRequest request,
      @RequestHeader("Token") String token,
      @RequestParam(value = "id", required = false) Integer id,
      @RequestParam(value = "nome", required = false) String nome,
      @RequestParam(value = "descricao", required = false) String descricao)
      throws BadRequestException {

    UsuarioModel usuario =
        tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

    return campoCustomizadoService.getCampoCustomizado(usuario, id, nome, descricao);
  }

  @PostMapping
  public CampoCustomizadoModel postCampoCustomizado(HttpServletRequest request,
      @RequestHeader("Token") String token, @RequestBody CampoCustomizadoModel campoCustomizado)
      throws BadRequestException {

    UsuarioModel usuario =
        tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

    return campoCustomizadoService.postCampoCustomisado(usuario, campoCustomizado);
  }

  @PatchMapping
  public CampoCustomizadoModel atualizarCampoCustomizado(HttpServletRequest request,
      @RequestHeader("Token") String token, @RequestBody CampoCustomizadoModel campoCustomizado)
      throws BadRequestException, ForbiddenException {

    UsuarioModel usuario =
        tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

    return campoCustomizadoService.atualizarCampoCustomizado(usuario, campoCustomizado);
  }


  @DeleteMapping("/{id}")
  public CampoCustomizadoModel desativarCampoCustomizado(HttpServletRequest request,
      @RequestHeader("Token") String token, @PathVariable("id") Integer idCampo)
      throws BadRequestException, ForbiddenException {

    UsuarioModel usuario =
        tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

    return campoCustomizadoService.desativarCampoCustomizado(usuario, idCampo);
  }
}
