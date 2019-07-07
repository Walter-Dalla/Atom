package br.com.cotil.aton.formularios.formulario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
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
			@RequestParam(value = "nomeFormulario", required = false) String nomeFormulario)
			throws BadRequestException {

		UsuarioModel usuario = tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

		return formularioService.listaFormularios(usuario, id, nomeFormulario);
	}

}
