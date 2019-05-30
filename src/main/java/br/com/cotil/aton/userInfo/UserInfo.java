package br.com.cotil.aton.userInfo;

import javax.persistence.Column;

public class UserInfo {

  @Column(name = "ID_USUARIO")
  private Integer idUsuario;

  @Column(name = "ID_EMPRESA")
  private Integer idEmpresa;

  @Column(name = "ID_GRUPO")
  private Integer idGrupo;

  @Column(name = "PERFIL_ORDEM")
  private String perfilOrdem;

  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public Integer getIdGrupo() {
    return idGrupo;
  }

  public void setIdGrupo(Integer idGrupo) {
    this.idGrupo = idGrupo;
  }

  public String getPerfilOrdem() {
    return perfilOrdem;
  }

  public void setPerfilOrdem(String perfilOrdem) {
    this.perfilOrdem = perfilOrdem;
  }

  public Integer getIdEmpresa() {
    return idEmpresa;
  }

  public void setIdEmpresa(Integer idEmpresa) {
    this.idEmpresa = idEmpresa;
  }

}
