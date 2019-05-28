package br.com.cotil.aton.empresa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "EMPRESA")
@EntityListeners(AuditingEntityListener.class)
public class EmpresaModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(name = "CNPJ")
  String CNPJ;
  
  @Column(name = "RAZAO_SOCIAL")
  String RazaoSocial;

  @Column(name = "E_MAIL")
  String eMail;

  @Column(name = "SITE")
  String Site;

  @Column(name = "TELEFONE")
  String Telefone;

  @CreatedDate
  @Column(name = "DATA_CRIACAO")
  Date dataCriacao;

  @LastModifiedDate
  @Column(name = "DATA_ATUALIZACAO")
  Date dataUltimaAtualizacao;

}
