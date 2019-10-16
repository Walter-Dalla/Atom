package br.com.cotil.aton.grupo.grupo;

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
import br.com.cotil.aton.usuario.token.TokenService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.RequestUtils;

@RestController
@RequestMapping("/grupo")
public class GrupoController {


  TokenService tokenService;
  GrupoService grupoService;

  @Autowired
  public GrupoController(TokenService tokenService, GrupoService grupoService) {
    super();
    this.tokenService = tokenService;
    this.grupoService = grupoService;
  }



  @GetMapping
  public Object getGrupo(HttpServletRequest request, @RequestHeader("Token") String token,
      @RequestParam(name = "idGrupo", required = false) Integer idGrupo,
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "20") Integer size) throws BadRequestException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return grupoService.getGrupos(usuario, idGrupo, page, size);
  }

  @PostMapping
  public GrupoModel createNewGrupo(HttpServletRequest request, @RequestHeader("Token") String token,
      @RequestBody GrupoModel novoGrupo) throws BadRequestException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return grupoService.createNewGrupo(usuario, novoGrupo);
  }

  @PatchMapping
  public GrupoModel updateGrupo(HttpServletRequest request, @RequestHeader("Token") String token,
      @RequestBody GrupoModel novoGrupo) throws BadRequestException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return grupoService.updateGrupos(novoGrupo, usuario);
  }


  @DeleteMapping("/{id}")
  public GrupoModel deleteGrupo(HttpServletRequest request, @RequestHeader("Token") String token,
      @PathVariable("id") Integer idGrupo) throws BadRequestException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return grupoService.deleteGrupo(idGrupo, usuario);
  }

}
