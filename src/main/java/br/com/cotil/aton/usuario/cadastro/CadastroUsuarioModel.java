package br.com.cotil.aton.usuario.cadastro;

public class CadastroUsuarioModel {


  private String password;

  private String userName;

  private String NomeCompleto;

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

  public String getNomeCompleto() {
    return NomeCompleto;
  }



  public void setNomeCompleto(String nomeCompleto) {
    NomeCompleto = nomeCompleto;
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
        + ", NomeCompleto=" + NomeCompleto + ", email=" + email + "]";
  }
}
