package br.com.cotil.aton.usuario.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ConflictException;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;


@RestController
@RequestMapping("/cadastrar")
public class CadastroController {

  @Autowired
  private CadastroUsuarioService cadastroUsuarioService;

  @PostMapping
  public UsuarioModel postMethodName(@RequestBody CadastroUsuarioModel cadastroUsuarioModel)
      throws ConflictException, BadRequestException {

    UsuarioModel usuarioModel = cadastroUsuarioService.cadastrarUsuario(cadastroUsuarioModel);

    return usuarioModel;
  }
}
