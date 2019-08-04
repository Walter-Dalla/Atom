package br.com.cotil.aton.grupo.grupoUsuario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/grupo/usuario")
public class GrupoUsuarioController {


  TokenService tokenService;
  GrupoUsuarioService grupoUsuarioService;

  @Autowired
  public GrupoUsuarioController(TokenService tokenService,
      GrupoUsuarioService grupoUsuarioService) {
    super();
    this.tokenService = tokenService;
    this.grupoUsuarioService = grupoUsuarioService;
  }



  @GetMapping
  public List<UsuarioModel> getGrupoUsuario(HttpServletRequest request,
      @RequestHeader("Token") String token, @RequestParam("idGrupo") Integer idGrupo)
      throws BadRequestException, ForbiddenException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return grupoUsuarioService.getUsuariosDoGrupo(usuario, idGrupo);
  }

  @PostMapping
  public GrupoUsuarioModel createNewGrupoUsuario(HttpServletRequest request,
      @RequestHeader("Token") String token, @RequestBody GrupoUsuarioModel grupoUsuario)
      throws BadRequestException, ForbiddenException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return grupoUsuarioService.createNewGrupoUsuario(grupoUsuario, usuario);
  }

  @DeleteMapping("/{id}")
  public GrupoUsuarioModel updateGrupo(HttpServletRequest request,
      @RequestHeader("Token") String token, @PathVariable("id") Integer idGrupoUsuario)
      throws BadRequestException, ForbiddenException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return grupoUsuarioService.deleteGrupoUsuario(idGrupoUsuario, usuario);
  }

}
