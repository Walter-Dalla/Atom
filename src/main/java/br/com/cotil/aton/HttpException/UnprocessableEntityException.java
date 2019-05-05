package br.com.cotil.aton.HttpException;

public class UnprocessableEntityException extends BaseException {

  private static final long serialVersionUID = 7756883911364224320L;

  public UnprocessableEntityException(String mensagem) {
    super("Invalid email", mensagem, 500);
  }
}
