package br.com.cotil.aton.formularios.campoCustomizado;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.formularios.formulario.FormularioModel;


@Entity
@Table(name = "CAMPO_FORMULARIO")
@EntityListeners(AuditingEntityListener.class)
public class CampoCustomizadoFormularioModel {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "ID_FORMULARIO")
  FormularioModel formulario;

  @OneToOne
  @JoinColumn(name = "ID_CAMPO_CUSTOMIZADO")
  CampoCustomizadoModel campoCustomizado;


}
