package br.com.cotil.aton.usuario.token.encrypt;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.usuario.conexao.ConexaoModel;
import br.com.cotil.aton.usuario.token.TokenModel;

public class TokenEncriptacao {

  public static String key = "Oi, Bom dia, Flor do dia";

  public TokenModel gerarToken(ConexaoModel conexaoModel, String requestIp)
      throws BadRequestException {
    String token;

    token = conexaoModel.getId() + ";" + conexaoModel.getNomeUsuario() + ";"
        + conexaoModel.getUsuario().getNome() + ";" + "Aton;" + ZonedDateTime
            .now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        + ";" + requestIp;

    token = token.replaceAll(" ", "");

    TokenModel tokenAcesso = new TokenModel();

    tokenAcesso.setConexao(conexaoModel);
    tokenAcesso.setUsuario(conexaoModel.getUsuario());

    AES.setKey(key);

    tokenAcesso.setToken(AES.encrypt(token, key));
    return tokenAcesso;
  }


  public List<String> decriptarToken(String token) throws BadRequestException {
    String dados = AES.decrypt(token, key);

    List<String> dadosList = Arrays.asList(dados.split(";"));
    Integer sizeSplitBySemicolun = dadosList.size();

    if (sizeSplitBySemicolun != 6)
      throw new BadRequestException("Token invalido");

    return dadosList;
  }
}
