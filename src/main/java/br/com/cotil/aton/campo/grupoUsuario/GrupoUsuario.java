package br.com.cotil.aton.campo.grupoUsuario;

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
import br.com.cotil.aton.usuario.UsuarioModel;

@Entity
@Table(name = "GRUPO_USUARIO")
@EntityListeners(AuditingEntityListener.class)
public class GrupoUsuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "ID_GRUPO")
  GrupoModel grupo;

  @OneToOne
  @JoinColumn(name = "ID_USUARIO")
  UsuarioModel usuario;

}
