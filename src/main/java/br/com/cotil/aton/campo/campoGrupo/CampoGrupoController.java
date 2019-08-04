package br.com.cotil.aton.campo.campoGrupo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/campo/grupo")
public class CampoGrupoController {

  CampoGrupoService campoGrupoService;
  TokenService tokenService;

  @Autowired
  public CampoGrupoController(CampoGrupoService campoGrupoService, TokenService tokenService) {
    super();
    this.campoGrupoService = campoGrupoService;
    this.tokenService = tokenService;
  }



  @GetMapping
  public Page<CampoGrupoModel> pegarCampoGrupo(HttpServletRequest request,
      @RequestHeader("Token") String token,
      @RequestParam(value = "idGrupo", required = false) Integer idGrupo,
      @RequestParam(value = "nomeGrupo", required = false) String nomeGrupo,
      @RequestParam(value = "descricaoGrupo", required = false) String descricaoGrupo,
      @RequestParam(value = "idCampo", required = false) Integer idCampo,
      @RequestParam(value = "nomeCampo", required = false) String nomeCampo,
      @RequestParam(value = "descricaoCampo", required = false) String descricaoCampo,
      @RequestParam(value = "ativo", required = false, defaultValue = "true") boolean ativo,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size) throws BadRequestException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return campoGrupoService.getCamposDosGruposDoUsuario(usuario, idGrupo, nomeGrupo,
        descricaoGrupo, idCampo, nomeCampo, descricaoCampo, ativo, page, size);
  }


  @PostMapping
  public CampoGrupoModel autorizarCampoParaUmGrupo(HttpServletRequest request,
      @RequestHeader("Token") String token, @RequestBody CampoGrupoModel campoGrupo)
      throws BadRequestException, ForbiddenException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return campoGrupoService.autorizarCampoParaUmGrupo(usuario, campoGrupo);
  }

  @DeleteMapping("/{id}")
  public CampoGrupoModel atualizarCampoParaUmGrupo(HttpServletRequest request,
      @RequestHeader("Token") String token, @PathVariable("id") Integer idCampoGrupo)
      throws BadRequestException, ForbiddenException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return campoGrupoService.desativarCampoParaUmGrupo(usuario, idCampoGrupo);
  }
}
