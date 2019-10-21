package br.com.cotil.aton.formularios.campoFormulario;

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
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.usuario.token.TokenService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.RequestUtils;

@RestController
@RequestMapping("/formulario/campo")
public class CampoFormularioController {

	TokenService tokenService;

	CampoFormularioService campoFormularioService;

	@Autowired
	public CampoFormularioController(TokenService tokenService, CampoFormularioService campoFormularioService) {
		super();
		this.tokenService = tokenService;
		this.campoFormularioService = campoFormularioService;
	}

	@GetMapping
	public List<CampoFormularioModel> listaFormularios(HttpServletRequest request, @RequestHeader("Token") String token,
			@RequestParam(value = "idFormulario") Integer idFormulario)
			throws BadRequestException, ForbiddenException, UnauthorizedException {

		UsuarioModel usuario = tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

		return campoFormularioService.listaCamposFormularios(usuario, idFormulario);
	}

	@PostMapping
	public CampoFormularioModel criaFormularios(HttpServletRequest request, @RequestHeader("Token") String token,
			@RequestBody CampoFormularioModel campoFormularioModel)
			throws BadRequestException, ForbiddenException, UnauthorizedException {

		UsuarioModel usuario = tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

		return campoFormularioService.criaFormulario(usuario, campoFormularioModel);
	}
	
	@DeleteMapping("/{idCampoFormulario}")
	public CampoFormularioModel desabilitaFormulario(HttpServletRequest request, @RequestHeader("Token") String token,
			@PathVariable("idCampoFormulario") Integer idCampoFormulario)
			throws BadRequestException, ForbiddenException {

		UsuarioModel usuario = tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

		return campoFormularioService.desabilitaFormulario(idCampoFormulario, usuario);
	}

}
