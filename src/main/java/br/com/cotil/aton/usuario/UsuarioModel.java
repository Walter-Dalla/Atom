package br.com.cotil.aton.usuario;

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

import br.com.cotil.aton.empresa.EmpresaModel;

@Entity
@Table(name = "USUARIO")
@EntityListeners(AuditingEntityListener.class)
public class UsuarioModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "ID_EMPRESA")
  private EmpresaModel empresa;

  @Column(name = "NOME_USUARIO")
  private String nome;

  @Column(name = "EMAIL_PRIMARIO")
  private String eMail;

  @Column(name = "TELEFONE")
  private String telefone;

  @Column(name = "CARGO")
  private String cargo;

  @Column(name = "CPF")
  private String CPF;

  @Column(name = "PERFIL_ORDEM")
  private String PerfilOrdem;

  @CreatedDate
  @Column(name = "DATA_CRIACAO")
  Date dataCriacao;

  @LastModifiedDate
  @Column(name = "DATA_ATUALIZACAO")
  Date dataUltimaAtualizacao;


}
