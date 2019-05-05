package br.com.cotil.aton.HttpException;

public class BaseException extends Exception {

  private static final long serialVersionUID = 1L;
  
  protected final String tipo;
  protected final String mensagem;
  protected final Integer codigo;

  public BaseException(String tipo, String mensagem, Integer codigo) {
    super();
    this.tipo = tipo;
    this.mensagem = mensagem;
    this.codigo = codigo;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getTipo() {
    return tipo;
  }

  public String getMensagem() {
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
