package br.com.cotil.aton.HttpException;

public class InternalServerError extends BaseException {

  private static final long serialVersionUID = 2051712521912057658L;

  public InternalServerError(String mensagem) {
    super("Internal Server Error", mensagem, 500);
  }
}
