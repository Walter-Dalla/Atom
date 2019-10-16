package br.com.cotil.aton.HttpException;

public class NotFoundException extends BaseException {

  private static final long serialVersionUID = -2493289553744300736L;

  public NotFoundException(String mensagem) {
    super("Not found", mensagem, 404);
  }

}
