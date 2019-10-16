package br.com.cotil.aton.HttpException;

public class ForbiddenException extends BaseException {

  private static final long serialVersionUID = -5849005813925210326L;

  public ForbiddenException(String mensagem) {
    super("Forbidden", mensagem, 403);
  }

}
