package br.com.cotil.aton.formularios.respostas.formularioResposta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.formularios.campoFormulario.CampoFormularioModel;
import br.com.cotil.aton.formularios.respostas.PostBodyModel;
import br.com.cotil.aton.usuario.token.TokenService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.RequestUtils;

@RestController
@RequestMapping("/formulario/resposta")
public class RespostaController {

	RespostaService respostaService;
	TokenService tokenService;

	@Autowired
	public RespostaController(RespostaService respostaService, TokenService tokenService) {
		super();
		this.respostaService = respostaService;
		this.tokenService = tokenService;
	}

	@GetMapping("/{id}")
	public List<CampoFormularioModel> getFormularioById(@PathVariable("id") Integer id)
			throws BadRequestException, UnauthorizedException {

		return respostaService.getFormulario(id);
	}
	
	@GetMapping
	public Page<CampoRespostaModel> getRespostas(HttpServletRequest request,
		      @RequestHeader("Token") String token, @RequestParam("id") Integer id,
		      @RequestParam("page") Integer page)
			throws BadRequestException, UnauthorizedException {
		
		 UsuarioModel usuario =
			        tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));
		
		return respostaService.getRespostas(id, page, usuario);
	}

	@PostMapping
	public Object respondeFormulario(@RequestBody PostBodyModel respostaModel)
			throws BadRequestException, UnauthorizedException {

		return respostaService.responderFormulario(respostaModel);
	}
}
