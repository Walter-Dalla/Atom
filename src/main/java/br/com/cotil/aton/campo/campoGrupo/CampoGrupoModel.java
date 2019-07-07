package br.com.cotil.aton.campo.campoGrupo;

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

import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.grupo.grupo.GrupoModel;
import br.com.cotil.aton.nivelPermissao.nivelPermissaoModel;

@Entity
@Table(name = "CAMPO_GRUPO")
@EntityListeners(AuditingEntityListener.class)
public class CampoGrupoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "ID_CAMPO_CUSTOMIZADO")
  CampoCustomizadoModel campoCustomizado;

  @OneToOne
  @JoinColumn(name = "ID_GRUPO")
  GrupoModel grupo;
  
  @OneToOne
  @JoinColumn(name = "ID_NIVEL_PERMISSAO")
  nivelPermissaoModel nivelPermissao;

  @CreatedDate
  @Column(name = "DATA_CRIACAO")
  private Date dataCriacao;

  @LastModifiedDate
  @Column(name = "DATA_ALTERACAO")
  private Date dataAlteracao;
  
  @Column(name = "ATIVO")
  boolean ativo;
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CampoCustomizadoModel getCampoCustomizado() {
    return campoCustomizado;
  }

  public void setCampoCustomizado(CampoCustomizadoModel campoCustomizado) {
    this.campoCustomizado = campoCustomizado;
  }

  public nivelPermissaoModel getNivelPermissao() {
    return nivelPermissao;
  }

  public void setNivelPermissao(nivelPermissaoModel nivelPermissao) {
    this.nivelPermissao = nivelPermissao;
  }

  public GrupoModel getGrupo() {
    return grupo;
  }

  public void setGrupo(GrupoModel grupo) {
    this.grupo = grupo;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public Date getDataAlteracao() {
    return dataAlteracao;
  }

  public void setDataAlteracao(Date dataAlteracao) {
    this.dataAlteracao = dataAlteracao;
  }
}
