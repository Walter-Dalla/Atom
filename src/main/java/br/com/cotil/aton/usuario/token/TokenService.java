package br.com.cotil.aton.usuario.token;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.usuario.conexao.ConexaoModel;
import br.com.cotil.aton.usuario.conexao.ConexaoRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class TokenService {

  TokenRepository tokenRepository;

  ConexaoRepository conexaoRepository;



  public static String key = "Oi, Bom dia, Flor do dia";

  @Autowired
  public TokenService(TokenRepository tokenRepository, ConexaoRepository conexaoRepository) {
    super();
    this.tokenRepository = tokenRepository;
    this.conexaoRepository = conexaoRepository;
  }

  public TokenModel GerarToken(ConexaoModel conexaoModel) throws BadRequestException {

    Optional<TokenModel> tokenDeAcessoOptional = tokenRepository.findByConexao(conexaoModel);

    if (tokenDeAcessoOptional.isPresent())
      return tokenDeAcessoOptional.get();

    String token;

    token = conexaoModel.getId() + ";" + conexaoModel.getNomeConexao() + ";"
        + conexaoModel.getUsaurio().getNome() + ";" + "Aton;" + ZonedDateTime
            .now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    token = token.replaceAll(" ", "");

    TokenModel tokenAcesso = new TokenModel();

    tokenAcesso.setConexao(conexaoModel);
    tokenAcesso.setUsuario(conexaoModel.getUsaurio());

    AES.setKey(key);

    tokenAcesso.setToken(AES.encrypt(token, key));

    tokenRepository.save(tokenAcesso);

    return tokenAcesso;

  }

  public UsuarioModel getDadosToken(String token) throws BadRequestException {

    String dados = AES.decrypt(token, key);

    List<String> dadosList = Arrays.asList(dados.split(";"));

    if (dadosList.size() != 5)
      throw new BadRequestException("Token invalido");

    
    Optional<ConexaoModel> conexaoOptional =
        conexaoRepository.findById(Integer.parseInt(dadosList.get(0)));

    ValidaToken(conexaoOptional, dadosList);
    
    return conexaoOptional.get().getUsaurio();
  }


  private void ValidaToken(Optional<ConexaoModel> conexaoOptional, List<String> dadosList)
      throws BadRequestException {

    if (!conexaoOptional.isPresent())
      throw new BadRequestException("Token sem conexção ou expirado");

    ConexaoModel conexao = conexaoOptional.get();

    if (!dadosList.contains(conexao.getNomeConexao()))
      throw new BadRequestException("Token sem conexção ou expirado");

    if (!dadosList.contains("Aton"))
      throw new BadRequestException("Token sem conexção ou expirado");
  }

}
