package br.com.cotil.aton.usuario.usuario;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.usuario.token.TokenService;
import br.com.cotil.aton.util.RequestUtils;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private UsuarioService UsuarioService;
  private TokenService tokenService;

  @Autowired
  public UsuarioController(br.com.cotil.aton.usuario.usuario.UsuarioService usuarioService,
      TokenService tokenService) {
    super();
    UsuarioService = usuarioService;
    this.tokenService = tokenService;
  }


  @GetMapping
  public UsuarioModel getUsuario(HttpServletRequest request, @RequestHeader("Token") String token)
      throws BadRequestException {

    return tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));
  }


  @PatchMapping
  public UsuarioModel alterarUsuario(HttpServletRequest request,
      @RequestHeader("Token") String token, @RequestBody UsuarioModel usuarioAlterado)
      throws BadRequestException, UnauthorizedException {

    UsuarioModel usuario = tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return UsuarioService.alterUser(token, usuarioAlterado, usuario);
  }

  @DeleteMapping
  public UsuarioModel desativarUsuario(HttpServletRequest request,
      @RequestHeader("Token") String token) throws BadRequestException {
    return UsuarioService.desativarUsuario(token, RequestUtils.getIpFromRequest(request));
  }
}
