package br.com.cotil.aton.formularios.formulario;

import java.util.List;

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
	public List<FormularioModel> listaFormularios(HttpServletRequest request, @RequestHeader("Token") String token,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nomeFormulario", required = false) String nomeFormulario,
			@RequestParam(value = "ativo", required = false, defaultValue = "true") boolean ativo) throws BadRequestException {

		UsuarioModel usuario = tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

		return formularioService.listaFormularios(usuario, id, nomeFormulario, ativo);
	}

	@PostMapping
	public FormularioModel criaFormularios(HttpServletRequest request, @RequestHeader("Token") String token,
			@RequestBody FormularioModel formulario) throws BadRequestException {

		UsuarioModel usuario = tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

		return formularioService.criaFormulario(usuario, formulario);
	}

	@PatchMapping
	public FormularioModel atualizaFormulario(HttpServletRequest request, @RequestHeader("Token") String token,
			@RequestBody FormularioModel formulario) throws BadRequestException, ForbiddenException {

		UsuarioModel usuario = tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

		return formularioService.atualizaFormulario(usuario, formulario);
	}

	@DeleteMapping("/{idFormulario}")
	public FormularioModel desabilitaFormulario(HttpServletRequest request, @RequestHeader("Token") String token,
			@PathVariable("idFormulario") Integer idFormulario) throws BadRequestException, ForbiddenException {

		UsuarioModel usuario = tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

		return formularioService.desabilitaFormulario(idFormulario, usuario);
	}

}
