package br.com.cotil.aton.formularios.respostas;

import java.util.List;

public class PostBodyModel {
	
	private Integer idFormulario;
	
	private List<ResponseModel> camposResposta;

	public Integer getIdFormulario() {
		return idFormulario;
	}

	public void setIdFormulario(Integer idFormulario) {
		this.idFormulario = idFormulario;
	}

	public List<ResponseModel> getCamposResposta() {
		return camposResposta;
	}

	public void setCamposResposta(List<ResponseModel> camposResposta) {
		this.camposResposta = camposResposta;
	}
	
	
	
}
