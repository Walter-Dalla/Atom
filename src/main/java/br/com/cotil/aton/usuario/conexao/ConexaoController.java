package br.com.cotil.aton.usuario.conexao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ConflictException;
import br.com.cotil.aton.usuario.token.TokenModel;


@RestController
@RequestMapping("/login")
public class ConexaoController {

  @Autowired
  private ConexaoService conexaoService;



  @PostMapping
  public TokenModel postMethodName(@RequestBody ConexaoModel conexaoModel)
      throws ConflictException, BadRequestException {

    TokenModel token = conexaoService.autorizar(conexaoModel);

    return token;
  }



}
