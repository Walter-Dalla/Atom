package br.com.cotil.aton.usuario.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.ConflictException;
import br.com.cotil.aton.usuario.cadastro.CadastroUsuarioModel;
import br.com.cotil.aton.usuario.cadastro.CadastroUsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  private CadastroUsuarioService cadastroUsuarioService;



  @PostMapping("/cadastrar")
  public UsuarioModel postMethodName(@RequestBody CadastroUsuarioModel cadastroUsuarioModel)
      throws ConflictException {

    UsuarioModel usuarioModel = cadastroUsuarioService.cadastrarUsuario(cadastroUsuarioModel);

    return usuarioModel;
  }



}
