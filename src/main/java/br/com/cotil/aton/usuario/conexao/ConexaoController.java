package br.com.cotil.aton.usuario.conexao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ConflictException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.usuario.token.TokenModel;
import br.com.cotil.aton.util.RequestUtils;


@RestController
@RequestMapping("/login")
public class ConexaoController {

  @Autowired
  private ConexaoService conexaoService;



  @PostMapping
  public TokenModel login(HttpServletRequest request, @RequestBody ConexaoModel conexaoModel)
      throws ConflictException, BadRequestException, ForbiddenException {

    TokenModel token =
        conexaoService.autorizar(conexaoModel, RequestUtils.getIpFromRequest(request));

    return token;
  }



}
