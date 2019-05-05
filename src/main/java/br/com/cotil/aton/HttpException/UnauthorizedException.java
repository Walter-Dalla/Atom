package br.com.cotil.aton.HttpException;

public class UnauthorizedException extends BaseException {

  private static final long serialVersionUID = -4627466015372594286L;

  public UnauthorizedException(String mensagem) {
    super("Unauthorized", mensagem, 401);
  }

}
