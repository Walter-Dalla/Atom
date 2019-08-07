package br.com.cotil.aton.campo.padrao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CAMPO_PADRAO")
@EntityListeners(AuditingEntityListener.class)
public class CampoPadraoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "NOME")
  private String nome;

  @Column(name = "TIPO")
  private String tipo;

  @Column(name = "DESCRICAO")
  private String descricao;

  @Column(name = "TOOL_TIP")
  private String toolTip;

  @Column(name = "PLACE_HOLDER")
  private String placeHolder;
  
  @Column(name = "ATIVO")
  private Boolean ativo;
  

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
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

  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }
  
}
