package br.com.cotil.aton.formularios.formularioAcesso;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.usuario.token.TokenService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.RequestUtils;

@RestController
@RequestMapping("/formulario/acesso")
public class FormularioAcessoController {

	TokenService tokenService;
	
	FormularioAcessoService formularioAcessoService;

	@GetMapping
	public List<FormularioAcesso> listaGruposUsuario(HttpServletRequest request, @RequestHeader("Token") String token,
			@RequestParam(value = "idGrupo", required = false) Integer idGrupo,
			@RequestParam(value = "nomeGrupo", required = false) String nomeGrupo,
			@RequestParam(value = "nomeFormulario", required = false) String nomeFormulario)
			throws BadRequestException {

		UsuarioModel usuario = tokenService.getDadosToken(token, RequestUtils.getIpFromRequest(request));

		return formularioAcessoService.listraGruposUsuario(usuario, idGrupo, nomeGrupo, nomeFormulario);

	}

	@PostMapping
	public FormularioAcesso criaFormularioAcesso(HttpServletRequest request, @RequestHeader("Token") String token,
			@RequestParam(value = "idUsuario", required = false) Integer idUsuario,
			@RequestParam(value = "idGrupo", required = false) Integer idGrupo) {

		return null;
	}

}
