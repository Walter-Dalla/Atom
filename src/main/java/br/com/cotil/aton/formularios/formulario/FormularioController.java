package br.com.cotil.aton.formularios.formulario;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/formulario")
public class FormularioController {

  TokenService tokenService;

  FormularioService formularioService;

  @Autowired
  public FormularioController(TokenService tokenService, FormularioService formularioService) {
    super();
    this.tokenService = tokenService;
    this.formularioService = formularioService;
  }

  @GetMapping
  public Page<FormularioModel> listaFormularios(HttpServletRequest request,
      @RequestHeader(value="Token", required=false) String token,
      @RequestParam(value = "id", required = false) Integer id,
      @RequestParam(value = "nomeFormulario", required = false) String nomeFormulario,
      @RequestParam(value = "ativo", required = false, defaultValue = "true") boolean ativo,
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "20") Integer size) throws BadRequestException {

    UsuarioModel usuario = null;
    try {
    	usuario = tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));
    }catch (Exception e) {
    	return formularioService.listaFormularios(id, nomeFormulario, ativo, page, size);
	}
    return formularioService.listaFormularios(usuario, id, nomeFormulario, ativo, page, size);
  }

  @PostMapping
  public FormularioModel criaFormularios(HttpServletRequest request,
      @RequestHeader("Token") String token, @RequestBody FormularioModel formulario)
      throws BadRequestException, ForbiddenException {

	  UsuarioModel usuario =
			  tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));
	  
    return formularioService.criaFormulario(usuario, formulario);
  }

  @PatchMapping
  public FormularioModel atualizaFormulario(HttpServletRequest request,
      @RequestHeader("Token") String token, @RequestBody FormularioModel formulario)
      throws BadRequestException, ForbiddenException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return formularioService.atualizaFormulario(usuario, formulario);
  }
  
  @PatchMapping("/{idFormulario}")
  public FormularioModel publicaFormulario(HttpServletRequest request,
      @RequestHeader("Token") String token, @PathVariable("idFormulario") Integer idFormulario)
      throws BadRequestException, ForbiddenException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return formularioService.publicaFormulario(usuario, idFormulario);
  }

  @DeleteMapping("/{idFormulario}")
  public FormularioModel desabilitaFormulario(HttpServletRequest request,
      @RequestHeader("Token") String token, @PathVariable("idFormulario") Integer idFormulario)
      throws BadRequestException, ForbiddenException {

    UsuarioModel usuario =
        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return formularioService.desabilitaFormulario(idFormulario, usuario);
  }

}
