package br.com.cotil.aton.campo.customisado;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.cotil.aton.campo.padrao.CampoPadraoModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Entity
@Table(name = "CAMPO_CUSTOMIZADO")
@EntityListeners(AuditingEntityListener.class)
public class CampoCustomizadoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "ID_USUARIO")
  private UsuarioModel usuario;

  @OneToOne
  @JoinColumn(name = "ID_CAMPO_PADRAO")
  private CampoPadraoModel campoPadrao;

  @Column(name = "NOME")
  private String nome;

  @CreatedDate
  @Column(name = "DATA_CRIACAO")
  private Date dataCriacao;

  @LastModifiedDate
  @Column(name = "DATA_ALTERACAO")
  private Date dataAlteracao;

  @Column(name = "DESCRICAO")
  private String descricao;

  @Column(name = "TOOL_TIP")
  private String toolTip;

  @Column(name = "PLACE_HOLDER")
  private String placeHolder;

  @Column(name = "ATIVO")
  private boolean ativo;
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public UsuarioModel getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioModel usuario) {
    this.usuario = usuario;
  }

  public CampoPadraoModel getCampoPadrao() {
    return campoPadrao;
  }

  public void setCampoPadrao(CampoPadraoModel campoPadrao) {
    this.campoPadrao = campoPadrao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
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

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getToolTip() {
    return toolTip;
  }

  public void setToolTip(String toolTip) {
    this.toolTip = toolTip;
  }

  public String getPlaceHolder() {
    return placeHolder;
  }

  public void setPlaceHolder(String placeHolder) {
    this.placeHolder = placeHolder;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }
}
