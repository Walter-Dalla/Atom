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

  @Column(name = "NOME_CONEXAO")
  String nomeConexao;

  @Column(name = "PASS")
  String pass;

  @OneToOne
  @JoinColumn(name = "ID_USUARIO")
  private UsuarioModel usaurio;

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

  public String getNomeConexao() {
    return nomeConexao;
  }

  public void setNomeConexao(String nomeConexao) {
    this.nomeConexao = nomeConexao;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public UsuarioModel getUsaurio() {
    return usaurio;
  }

  public void setUsaurio(UsuarioModel usaurio) {
    this.usaurio = usaurio;
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
    return "ConexaoModel [id=" + id + ", nomeConexao=" + nomeConexao + ", pass=" + pass
        + ", usaurio=" + usaurio + ", dataCriacao=" + dataCriacao + ", dataUltimaAtualizacao="
        + dataUltimaAtualizacao + "]";
  }
  
}
