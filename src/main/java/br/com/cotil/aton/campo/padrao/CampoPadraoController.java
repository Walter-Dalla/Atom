package br.com.cotil.aton.campo.padrao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.usuario.token.TokenService;
import br.com.cotil.aton.util.RequestUtils;

@RestController
@RequestMapping("/campo/padrao")
public class CampoPadraoController {

  CampoPadraoService campoPadraoService;
  TokenService tokenService;

  @Autowired
  public CampoPadraoController(CampoPadraoService campoPadraoService, TokenService tokenService) {
    this.campoPadraoService = campoPadraoService;
    this.tokenService = tokenService;
  }

  @GetMapping
  public Page<CampoPadraoModel> getCamposPadrao(HttpServletRequest request,
      @RequestHeader("Token") String token,
      @RequestParam(value = "id", required = false) Integer id,
      @RequestParam(value = "nome", required = false) String nome,
      @RequestParam(value = "descricao", required = false) String descricao,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "20") Integer size) throws BadRequestException {

    tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return campoPadraoService.getCamposPadrao(id, nome, descricao, page, size);
  }


}
