package br.com.cotil.aton.HttpException;

public class ConflictException extends BaseException {

  private static final long serialVersionUID = -1003441662078175176L;

  public ConflictException(String mensagem) {
    super("Conflict", mensagem, 409);
  }

}
