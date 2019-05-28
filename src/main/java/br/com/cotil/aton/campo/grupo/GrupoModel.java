package br.com.cotil.aton.campo.grupo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.cotil.aton.empresa.EmpresaModel;

@Entity
@Table(name = "GRUPO")
@EntityListeners(AuditingEntityListener.class)
public class GrupoModel {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "ID_EMPRESA")
  EmpresaModel empresa;

  @Column(name = "NOME")
  String nome;

  @Column(name = "DESCRICAO")
  String descricao;



}
