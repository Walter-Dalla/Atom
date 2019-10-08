package br.com.cotil.aton.campo.customisado;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cotil.aton.campo.padrao.CampoPadraoModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Entity
@Table(name = "CAMPO_CUSTOMIZADO")
@EntityListeners(AuditingEntityListener.class)
public class CampoCustomizadoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	@JsonIgnore
	private UsuarioModel usuario;

	@OneToOne
	@JoinColumn(name = "ID_CAMPO_PADRAO")
	private CampoPadraoModel campoPadrao;

	@Column(name = "NOME")
	private String nome;

	@CreatedDate
	@Column(name = "DATA_CRIACAO")
	private Date dataCriacao;

	@LastModifiedDate
	@Column(name = "DATA_ALTERACAO")
	private Date dataAlteracao;

	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "TOOL_TIP")
	private String toolTip;

	@Column(name = "PLACE_HOLDER")
	private String placeholder;
	
	@Column(name = "LABEL")
	private String label;
	
	@Column(name = "MAX_LENGHT")
	private Integer maxlenght;
	
	@Column(name = "MIN_LENGHT")
	private Integer minlenght;

	@Column(name = "DEFAULT_VALUE")
	private String defaultValue;
	
	@Column(name = "ATIVO")
	private boolean ativo;
	
	@Column(name = "MARCADO")
	private boolean marcado;
	
	public CampoCustomizadoModel() {}
	
	public CampoCustomizadoModel(CampoCustomizadoModel campoCustomizadoModel) {
		super();
		this.id = campoCustomizadoModel.getId();
		this.usuario = campoCustomizadoModel.getUsuario();
		this.campoPadrao = campoCustomizadoModel.getCampoPadrao();
		this.nome = campoCustomizadoModel.getNome();
		this.dataCriacao = campoCustomizadoModel.getDataCriacao();
		this.dataAlteracao = campoCustomizadoModel.getDataAlteracao();
		this.type = campoCustomizadoModel.getType();
		this.toolTip = campoCustomizadoModel.getToolTip();
		this.placeholder = campoCustomizadoModel.getPlaceholder();
		this.label = campoCustomizadoModel.getLabel();
		this.maxlenght = campoCustomizadoModel.getMaxlenght();
		this.minlenght = campoCustomizadoModel.getMinlenght();
		this.defaultValue = campoCustomizadoModel.getDefaultValue();
		this.ativo = campoCustomizadoModel.isAtivo();
		this.marcado = campoCustomizadoModel.isMarcado();
	}

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

	public CampoPadraoModel getCampoPadrao() {
		return campoPadrao;
	}

	public void setCampoPadrao(CampoPadraoModel campoPadrao) {
		this.campoPadrao = campoPadrao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToolTip() {
		return toolTip;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeHolder) {
		this.placeholder = placeHolder;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getMaxlenght() {
		return maxlenght;
	}

	public void setMaxlenght(Integer maxlenght) {
		this.maxlenght = maxlenght;
	}

	public Integer getMinlenght() {
		return minlenght;
	}

	public void setMinlenght(Integer minlenght) {
		this.minlenght = minlenght;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isAtivo() {
		return ativo;
	}
	
	public boolean isMarcado() {
		return marcado;
	}

	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
