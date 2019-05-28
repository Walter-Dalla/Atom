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

import br.com.cotil.aton.campo.grupo.GrupoModel;
import br.com.cotil.aton.formularios.formulario.FormularioModel;
import br.com.cotil.aton.nivelPermissao.nivelPermissaoModel;

@Entity
@Table(name = "FORMULARIO_ACESSO")
@EntityListeners(AuditingEntityListener.class)
public class FormularioAcesso {


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

}
