package br.com.cotil.aton.formularios.formulario;

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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Entity
@Table(name = "FORMULARIO")
@EntityListeners(AuditingEntityListener.class)
public class FormularioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "ID_USUARIO")
	private UsuarioModel usuario;

	@Column(name = "NOME")
	private String nomeFormulario;

	@Column(name = "COMPARTILHAVEL")
	private boolean compartilhavel;
	
	@Column(name = "ATIVO")
	private boolean ativo;

	@CreatedDate
	@Column(name = "DATA_CRIACAO")
	private Date dataCriacao;

	@LastModifiedDate
	@Column(name = "DATA_ATUALIZACAO")
	private Date dataUltimaAtualizacao;
	
	@Column(name = "PUBLICADO")
	private boolean publicado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public String getNomeFormulario() {
		return nomeFormulario;
	}

	public void setNomeFormulario(String nomeFormulario) {
		this.nomeFormulario = nomeFormulario;
	}

	public boolean isCompartilhavel() {
		return compartilhavel;
	}

	public void setCompartilhavel(boolean compartilhavel) {
		this.compartilhavel = compartilhavel;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isPublicado() {
		return publicado;
	}

	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}

	
	
}
