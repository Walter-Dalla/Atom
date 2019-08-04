package br.com.cotil.aton.usuario.cadastro;

import br.com.cotil.aton.usuario.conexao.ConexaoModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

public class CadastroUtils {


  public static UsuarioModel createUsuarioModel(CadastroUsuarioModel cadastroUsuarioModel) {

    UsuarioModel usuario = new UsuarioModel();

    usuario.setEmail(cadastroUsuarioModel.getEmail());
    usuario.setNome(cadastroUsuarioModel.getNomeCompleto());
    usuario.setAtivo(true);

    return usuario;
  }

  public static ConexaoModel createConexaoModel(CadastroUsuarioModel cadastroUsuarioModel) {

    ConexaoModel con = new ConexaoModel();

    con.setNomeUsuario(cadastroUsuarioModel.getUserName());
    con.setPassword(cadastroUsuarioModel.getPassword());

    return con;
  }

}
