package br.com.cotil.aton.usuario.token;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.usuario.conexao.ConexaoModel;

@Service
public class TokenService {

  public static String key = "Oi, Bom dia, Flor do dia";

  public TokenModel GerarToken(ConexaoModel conexaoModel) throws BadRequestException {

    String token;

    token = conexaoModel.getNomeConexao() + ";" + conexaoModel.getId() + ";"
        + conexaoModel.getUsaurio().getNome() + ";" + "Aton" + ZonedDateTime
            .now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    TokenModel tokenAcesso = new TokenModel();
    
    tokenAcesso.setConexao(conexaoModel);
    tokenAcesso.setUsaurio(conexaoModel.getUsaurio());
    
    tokenAcesso.setToken(encriptar(token));
    System.out.println(tokenAcesso.getToken());
    System.out.println(dencriptar(tokenAcesso.getToken()));
    return tokenAcesso;

  }

  public String encriptar(String text) throws BadRequestException {
    try {
      Cipher cipher = createCipher();

      cipher.init(Cipher.ENCRYPT_MODE, createAesKey());
      byte[] encrypted = cipher.doFinal(text.getBytes());
      return encrypted.toString();

    } catch (Exception e) {
      e.printStackTrace();
      throw new BadRequestException("Erro ao gerar o token");
    }
  }


  public String dencriptar(String text) throws BadRequestException {
    try {
      Cipher cipher = createCipher();
      
      cipher.init(Cipher.DECRYPT_MODE, createAesKey());
      String decrypted = new String(cipher.doFinal(text.getBytes()));
      
      return decrypted;
      
    } catch (Exception e) {
      e.printStackTrace();
      throw new BadRequestException("Erro ao gerar o token");
    }
  }

  private Key createAesKey() {
    return new SecretKeySpec(key.getBytes(), "AES");
  }

  private Cipher createCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
    return Cipher.getInstance("AES");
  }


}
