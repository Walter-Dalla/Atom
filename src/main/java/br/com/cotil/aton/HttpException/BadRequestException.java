package br.com.cotil.aton.HttpException;

import java.util.List;

public class BadRequestException extends BaseException {

  private static final long serialVersionUID = -5894500780841176189L;

  public BadRequestException(String mensagem) {
    super("Bad Request", mensagem, 400);
  }

  public BadRequestException(List<String> mensagem) {
    super("Bad Request", mensagem, 400);
  }

}
