package br.com.cotil.aton.usuario.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.ConflictException;
import br.com.cotil.aton.usuario.conexao.ConexaoModel;
import br.com.cotil.aton.usuario.conexao.ConexaoRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.usuario.usuario.UsuarioRepository;

@Service
public class CadastroUsuarioService {

  UsuarioRepository usuarioRepository;

  ConexaoRepository conexaoRepository;

  @Autowired
  public CadastroUsuarioService(UsuarioRepository usuarioRepository,
      ConexaoRepository conexaoRepository) {
    super();
    this.usuarioRepository = usuarioRepository;
    this.conexaoRepository = conexaoRepository;
  }

  public UsuarioModel cadastrarUsuario(CadastroUsuarioModel cadastroUsuarioModel)
      throws ConflictException {

    UsuarioModel usuarioModel = CadastroUtils.createUsuarioModel(cadastroUsuarioModel);
    ConexaoModel conexao = CadastroUtils.createConexaoModel(cadastroUsuarioModel);

    validacaoAoCriarUsuario(conexao);

    conexao.setUsaurio(usuarioRepository.save(usuarioModel));
    
    conexaoRepository.save(conexao);
    
    return usuarioModel;
  }

  private void validacaoAoCriarUsuario(ConexaoModel conexao) throws ConflictException {

    boolean existe = conexaoRepository.existsByNomeConexao(conexao.getNomeConexao());

    if (existe)
      throw new ConflictException("Nome de usuario já existente");

  }



}
