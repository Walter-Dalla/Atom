package br.com.cotil.aton.HttpException;

public class BadRequestException extends BaseException {

  private static final long serialVersionUID = -5894500780841176189L;

  public BadRequestException(String mensagem) {
    super("Bad Request", mensagem, 400);
  }

}
