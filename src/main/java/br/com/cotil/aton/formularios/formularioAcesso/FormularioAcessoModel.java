package br.com.cotil.aton.formularios.formularioAcesso;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.cotil.aton.formularios.formulario.FormularioModel;
import br.com.cotil.aton.grupo.grupo.GrupoModel;
import br.com.cotil.aton.nivelPermissao.nivelPermissaoModel;

@Entity
@Table(name = "FORMULARIO_ACESSO")
@EntityListeners(AuditingEntityListener.class)
public class FormularioAcessoModel {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "ID_GRUPO")
  GrupoModel grupo;

  @OneToOne
  @JoinColumn(name = "ID_FORMULARIO")
  FormularioModel formulario;

  @OneToOne
  @JoinColumn(name = "ID_NIVEL_PERMISSAO")
  nivelPermissaoModel nivelPermissao;

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

  public FormularioModel getFormulario() {
    return formulario;
  }

  public void setFormulario(FormularioModel formulario) {
    this.formulario = formulario;
  }

  public nivelPermissaoModel getNivelPermissao() {
    return nivelPermissao;
  }

  public void setNivelPermissao(nivelPermissaoModel nivelPermissao) {
    this.nivelPermissao = nivelPermissao;
  }

  
  
}
