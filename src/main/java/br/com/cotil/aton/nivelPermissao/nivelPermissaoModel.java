package br.com.cotil.aton.nivelPermissao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "NIVEL_PERMISSAO")
@EntityListeners(AuditingEntityListener.class)
public class nivelPermissaoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  
  @Column(name = "NIVEL_PERMISSAO")
  Integer nivelPermissao;
  
  @Column(name = "DESCRICAO")
  String descricao;
  
}
