package br.com.cotil.aton.campo.campoGrupo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.usuario.token.TokenService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.RequestUtils;

@RestController
@RequestMapping("/campo/customizado")
public class CampoGrupoController {

  CampoGrupoService campoGrupoService;
  TokenService tokenService;

  @GetMapping
  public List<CampoCustomizadoModel> getCampoGrupo(HttpServletRequest request,
      @RequestHeader("Token") String token,
      @RequestParam(value = "idGrupo", required = false) Integer idGrupo,
      @RequestParam(value = "nomeGrupo", required = false) String nomeGrupo,
      @RequestParam(value = "descricaoGrupo", required = false) String descricaoGrupo,
      @RequestParam(value = "idCampo", required = false) Integer idCampo,
      @RequestParam(value = "nomeCampo", required = false) String nomeCampo,
      @RequestParam(value = "descricaoCampo", required = false) String descricaoCampo)
      throws BadRequestException {

    UsuarioModel usuario =
        tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

    return campoGrupoService.getCamposDosGruposDoUsuario(usuario, idGrupo, nomeGrupo, descricaoGrupo, idCampo, nomeCampo, descricaoCampo);
  }



}
