package br.com.cotil.aton.campo.grupo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "GRUPO")
@EntityListeners(AuditingEntityListener.class)
public class GrupoModel {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(name = "NOME")
  String nome;

  @Column(name = "DESCRICAO")
  String descricao;



}
