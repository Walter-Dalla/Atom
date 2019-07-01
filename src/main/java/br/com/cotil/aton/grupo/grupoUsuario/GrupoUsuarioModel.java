package br.com.cotil.aton.grupo.grupoUsuario;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cotil.aton.grupo.grupo.GrupoModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Entity
@Table(name = "GRUPO_USUARIO")
@EntityListeners(AuditingEntityListener.class)
public class GrupoUsuarioModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "ID_GRUPO")
  GrupoModel grupo;

  @OneToOne
  @JsonIgnore
  @JoinColumn(name = "ID_USUARIO")
  UsuarioModel usuario;

  @Column(name = "ATIVO", updatable = false)
  boolean ativo;

  @CreatedDate
  @Column(name = "DATA_CRIACAO", updatable = false)
  Date dataCriacao;

  @LastModifiedDate
  @Column(name = "DATA_ATUALIZACAO", updatable = false)
  Date dataUltimaAtualizacao;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public GrupoModel getGrupo() {
    return grupo;
  }

  public void setGrupo(GrupoModel grupo) {
    this.grupo = grupo;
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

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

}
