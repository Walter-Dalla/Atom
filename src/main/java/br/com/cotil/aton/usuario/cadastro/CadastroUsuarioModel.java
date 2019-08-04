package br.com.cotil.aton.usuario.cadastro;

public class CadastroUsuarioModel {


  private String password;

  private String userName;

  private String nomeExibicao;

  private String email;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getNomeExibicao() {
    return nomeExibicao;
  }

  public void setNomeExibicao(String nomeCompleto) {
    this.nomeExibicao = nomeCompleto;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public String toString() {
    return "CadastroUsuarioModel [password=" + password + ", userName=" + userName
        + ", nomeCompleto=" + nomeExibicao + ", email=" + email + "]";
  }
}
