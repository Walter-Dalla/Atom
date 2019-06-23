package br.com.cotil.aton.usuario.conexao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ConflictException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.usuario.token.TokenModel;
import br.com.cotil.aton.usuario.token.TokenService;
import br.com.cotil.aton.usuario.usuario.UsuarioRepository;

@Service
public class ConexaoService {

  UsuarioRepository usuarioRepository;

  ConexaoRepository conexaoRepository;

  TokenService tokenService;

  @Autowired
  public ConexaoService(UsuarioRepository usuarioRepository, ConexaoRepository conexaoRepository,
      TokenService tokenService) {
    super();
    this.usuarioRepository = usuarioRepository;
    this.conexaoRepository = conexaoRepository;
    this.tokenService = tokenService;
  }

  public TokenModel autorizar(ConexaoModel conexaoModel, String ip)
      throws ConflictException, BadRequestException, ForbiddenException {

    ConexaoModel conexao = validarConexao(conexaoModel);

    TokenModel token = tokenService.GerarToken(conexao, ip);

    return token;
  }

  private ConexaoModel validarConexao(ConexaoModel conexao)
      throws ConflictException, BadRequestException {

    Optional<ConexaoModel> conexaoOpcional =
        conexaoRepository.findByNomeConexaoAndPass(conexao.getNomeConexao(), conexao.getPass());

    if (!conexaoOpcional.isPresent())
      throw new BadRequestException("Usuario não encontrado");

    return conexaoOpcional.get();
  }



}
