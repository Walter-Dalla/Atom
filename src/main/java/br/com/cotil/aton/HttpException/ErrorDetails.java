package br.com.cotil.aton.HttpException;

public class ErrorDetails {

  private Object mensagem;
  private String tipo;
  private int codigo;

  public ErrorDetails(Object mensagem, String tipo, int codigo) {
    super();
    this.mensagem = mensagem;
    this.tipo = tipo;
    this.codigo = codigo;
  }

  public Object getMensagem() {
    return mensagem;
  }

  public void setMensagem(Object mensagem) {
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
