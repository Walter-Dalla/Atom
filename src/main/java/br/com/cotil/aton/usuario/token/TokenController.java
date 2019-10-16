package br.com.cotil.aton.usuario.token;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ConflictException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.usuario.conexao.ConexaoModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.RequestUtils;

@RestController
@RequestMapping("/token")
public class TokenController {

  TokenService tokenService;

  @Autowired
  TokenController(TokenService tokenService) {
    this.tokenService = tokenService;
  }


  @GetMapping("/user/info")
  public UsuarioModel getUserInfo(HttpServletRequest request, @RequestHeader("Token") String token)
      throws ForbiddenException, BadRequestException, ConflictException {

    return tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));
  }

  @PostMapping("/login")
  public TokenModel login(HttpServletRequest request, @RequestBody ConexaoModel conexaoModel)
      throws ConflictException, BadRequestException, ForbiddenException {

    TokenModel token =
        tokenService.getToken(conexaoModel, RequestUtils.getIpFromRequest(request));

    return token;
  }


}
