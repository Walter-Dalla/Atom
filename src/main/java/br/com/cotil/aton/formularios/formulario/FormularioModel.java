package br.com.cotil.aton.formularios.formulario;

import java.sql.Date;

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
@Table(name = "FORMULARIO")
@EntityListeners(AuditingEntityListener.class)
public class FormularioModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "ID_USUARIO")
  UsuarioModel usuario;

  @Column(name = "NOME")
  String nomeFormulario;

  @Column(name = "COMPARTILHAVEL")
  boolean compartilhavel;

  @CreatedDate
  @Column(name = "DATA_CRIACAO")
  Date dataCriacao;

  @LastModifiedDate
  @Column(name = "DATA_ATUALIZACAO")
  Date dataUltimaAtualizacao;

}
