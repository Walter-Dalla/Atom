package br.com.cotil.aton.HttpException;

public class BaseException extends Exception {

  private static final long serialVersionUID = 1L;

  protected final String tipo;
  protected final Object mensagem;
  protected final Integer codigo;

  public BaseException(String tipo, Object mensagem, Integer codigo) {
    super();
    this.tipo = tipo;
    this.codigo = codigo;
    this.mensagem = mensagem;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getTipo() {
    return tipo;
  }

  public Object getMensagem() {
    return mensagem;
  }

  public Integer getCodigo() {
    return codigo;
  }

  @Override
  public String toString() {
    return "BaseException [tipo=" + tipo + ", mensagem=" + mensagem + ", codigo=" + codigo + "]";
  }
}
