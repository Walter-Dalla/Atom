package br.com.cotil.aton.usuario.conexao;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Entity
@Table(name = "CONEXAO")
@EntityListeners(AuditingEntityListener.class)
public class ConexaoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "NOME_USUARIO")
  String nomeUsuario;

  @Column(name = "PASSWORD")
  String password;

  @OneToOne
  @JoinColumn(name = "ID_USUARIO")
  private UsuarioModel usuario;

  @CreatedDate
  @Column(name = "DATA_CRIACAO")
  Date dataCriacao;

  @LastModifiedDate
  @Column(name = "DATA_ATUALIZACAO")
  Date dataUltimaAtualizacao;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNomeUsuario() {
    return nomeUsuario;
  }

  public void setNomeUsuario(String nomeUsuario) {
    this.nomeUsuario = nomeUsuario;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UsuarioModel getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioModel usuario) {
    this.usuario = usuario;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public Date getDataUltimaAtualizacao() {
    return dataUltimaAtualizacao;
  }

  public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
    this.dataUltimaAtualizacao = dataUltimaAtualizacao;
  }

  @Override
  public String toString() {
    return "ConexaoModel [id=" + id + ", nomeConexao=" + nomeUsuario + ", pass=" + password
        + ", usaurio=" + usuario + ", dataCriacao=" + dataCriacao + ", dataUltimaAtualizacao="
        + dataUltimaAtualizacao + "]";
  }
  
}
