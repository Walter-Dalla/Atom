package br.com.cotil.aton.HttpException;

public class NoContentException extends BaseException {

  private static final long serialVersionUID = 670625168067559009L;

  public NoContentException(String mensagem) {
    super("No Content", mensagem, 204);
  }
}
