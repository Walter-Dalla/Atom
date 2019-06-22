package br.com.cotil.aton.usuario.token;

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

import br.com.cotil.aton.usuario.conexao.ConexaoModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Entity
@Table(name = "CONEXAO")
@EntityListeners(AuditingEntityListener.class)
public class TokenModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "ID_USUARIO")
  private UsuarioModel usaurio;
  
  @OneToOne
  @JoinColumn(name = "ID_CONEXAO")
  private ConexaoModel conexao;

  @Column(name = "TOKEN")
  private String token;
  
  @CreatedDate
  @Column(name = "DATA_CRIACAO")
  private Date dataCriacao;

  @LastModifiedDate
  @Column(name = "DATA_ATUALIZACAO")
  private Date dataUltimaAtualizacao;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public UsuarioModel getUsaurio() {
    return usaurio;
  }

  public void setUsaurio(UsuarioModel usaurio) {
    this.usaurio = usaurio;
  }

  public ConexaoModel getConexao() {
    return conexao;
  }

  public void setConexao(ConexaoModel conexao) {
    this.conexao = conexao;
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

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
  
  
  
}
