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
  Integer idCampoPadrao;

  @Column(name = "NOME")
  String nome;
  
  @Column(name = "TIPO")
  String tipo;
  
  @Column(name = "DESCRICAO")
  String descricao;
  
  @Column(name = "TOOL_TIP")
  String toolTip;
  
  @Column(name = "PLACE_HOLDER")
  String placeHolder;

  public Integer getIdCampoPadrao() {
    return idCampoPadrao;
  }

  public void setIdCampoPadrao(Integer idCampoPadrao) {
    this.idCampoPadrao = idCampoPadrao;
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

  @Override
  public String toString() {
    return "CampoPadraoModel [idCampoPadrao=" + idCampoPadrao + ", nome=" + nome + ", tipo=" + tipo
        + ", descricao=" + descricao + ", toolTip=" + toolTip + ", placeHolder=" + placeHolder
        + "]";
  }
}
