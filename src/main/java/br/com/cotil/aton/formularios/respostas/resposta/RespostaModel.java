package br.com.cotil.aton.formularios.respostas.resposta;

import java.util.Date;

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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.cotil.aton.formularios.formulario.FormularioModel;

@Entity
@Table(name = "RESPOSTA")
@EntityListeners(AuditingEntityListener.class)
public class RespostaModel {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;


	@OneToOne
	@JoinColumn(name = "ID_FORMULARIO")
	private FormularioModel formulario;
	
	@CreatedDate
	@Column(name = "DATA_CRIACAO")
	private Date dataCriacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public FormularioModel getFormulario() {
		return formulario;
	}

	public void setFormulario(FormularioModel formulario) {
		this.formulario = formulario;
	}
	
	
}
