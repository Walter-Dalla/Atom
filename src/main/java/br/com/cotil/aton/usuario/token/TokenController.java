package br.com.cotil.aton.usuario.token;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.RequestUtils;

@RestController
@RequestMapping("/user/info")
public class TokenController {

  TokenService tokenService;


  @Autowired
  TokenController(TokenService tokenService) {
    this.tokenService = tokenService;
  }


  @GetMapping
  public UsuarioModel getUserInfo(HttpServletRequest request, @RequestHeader("Token") String token)
      throws ForbiddenException, BadRequestException {

    return tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));
  }


}
