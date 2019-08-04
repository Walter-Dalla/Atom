package br.com.cotil.aton.usuario.token;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ConflictException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.usuario.conexao.ConexaoModel;
import br.com.cotil.aton.usuario.conexao.ConexaoService;
import br.com.cotil.aton.usuario.token.encrypt.TokenEncriptacao;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class TokenService {

  private TokenRepository tokenRepository;
  private ConexaoService conexaoService;
  private TokenEncriptacao tokenEncriptacao = new TokenEncriptacao();

  @Autowired
  public TokenService(TokenRepository tokenRepository, ConexaoService conexaoService) {
    super();
    this.tokenRepository = tokenRepository;
    this.conexaoService = conexaoService;
  }

  public TokenModel gerarToken(ConexaoModel conexaoModel, String requestIp)
      throws BadRequestException, ForbiddenException {

    TokenUtils.validaUsuario(conexaoModel);

    deletaTokenAtivoNoBanco(conexaoModel);

    return tokenRepository.save(tokenEncriptacao.gerarToken(conexaoModel, requestIp));
  }

  public UsuarioModel getUsuarioByToken(String token, String ip) throws BadRequestException {

    List<String> dadosList = tokenEncriptacao.decriptarToken(token);

    ConexaoModel conexao = conexaoService.validarConexao(Integer.parseInt(dadosList.get(0)));

    TokenUtils.validaToken(conexao, dadosList, ip);

    return conexao.getUsaurio();
  }

  public TokenModel getToken(ConexaoModel conexaoModel, String ip)
      throws ConflictException, BadRequestException, ForbiddenException {

    ConexaoModel conexao = conexaoService.validarConexao(conexaoModel);

    TokenModel token = gerarToken(conexao, ip);

    return token;
  }


  public void deletaTokenAtivoNoBanco(ConexaoModel conexaoModel) {

    Optional<TokenModel> tokenDeAcessoOptional = tokenRepository.findByConexao(conexaoModel);

    if (tokenDeAcessoOptional.isPresent())
      tokenRepository.deleteById(tokenDeAcessoOptional.get().getId());

  }
}
