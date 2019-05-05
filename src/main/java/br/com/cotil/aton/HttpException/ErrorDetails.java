package br.com.cotil.aton.HttpException;

public class ErrorDetails {

  private String mensagem;
  private String tipo;
  private int codigo;

  public ErrorDetails(String mensagem, String tipo, int codigo) {
    super();
    this.mensagem = mensagem;
    this.tipo = tipo;
    this.codigo = codigo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  @Override
  public String toString() {
    return "ErrorDetails [mensagem=" + mensagem + ", tipo=" + tipo + ", codigo=" + codigo + "]";
  }
}
