package br.com.cotil.aton.formularios.respostas.formularioResposta;

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

import br.com.cotil.aton.formularios.campoFormulario.CampoFormularioModel;
import br.com.cotil.aton.formularios.respostas.resposta.RespostaModel;

@Entity
@Table(name = "CAMPO_RESPOSTA")
@EntityListeners(AuditingEntityListener.class)
public class CampoRespostaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Integer id;

	@Column(name = "RESPOSTA")
	private String valor;

	@OneToOne
	@JoinColumn(name = "CAMPO_CUSTOMIZADO")
	private CampoFormularioModel campoFormulario;


	@OneToOne
	@JoinColumn(name = "ID_RESPOSTA")
	private RespostaModel resposta;

	@CreatedDate
	@Column(name = "DATA_CRIACAO")
	private Date dataCriacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public CampoFormularioModel getCampoFormulario() {
		return campoFormulario;
	}

	public void setCampoFormulario(CampoFormularioModel campoFormulario) {
		this.campoFormulario = campoFormulario;
	}

	public RespostaModel getResposta() {
		return resposta;
	}

	public void setResposta(RespostaModel resposta) {
		this.resposta = resposta;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
