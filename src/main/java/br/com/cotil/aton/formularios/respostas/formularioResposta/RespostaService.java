package br.com.cotil.aton.formularios.respostas.formularioResposta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.formularios.campoFormulario.CampoFormularioModel;
import br.com.cotil.aton.formularios.campoFormulario.CampoFormularioService;
import br.com.cotil.aton.formularios.formulario.FormularioModel;
import br.com.cotil.aton.formularios.formulario.FormularioService;
import br.com.cotil.aton.formularios.respostas.PostBodyModel;
import br.com.cotil.aton.formularios.respostas.ResponseModel;
import br.com.cotil.aton.formularios.respostas.resposta.RespostaModel;
import br.com.cotil.aton.formularios.respostas.resposta.RespostaRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

@Service
public class RespostaService {

	FormularioService formularioService;
	CampoFormularioService campoFormularioService;
	CampoRespostaRepository campoRespostaRepository;
	RespostaRepository respostaRepository;

	@Autowired
	public RespostaService(FormularioService formularioService, CampoFormularioService campoFormularioService,
			CampoRespostaRepository campoRespostaRepository, RespostaRepository respostaRepository) {
		super();
		this.formularioService = formularioService;
		this.campoFormularioService = campoFormularioService;
		this.campoRespostaRepository = campoRespostaRepository;
		this.respostaRepository = respostaRepository;
	}

	public List<CampoFormularioModel> getFormulario(Integer id) throws BadRequestException, UnauthorizedException {

		FormularioModel formulario = formularioService.pegaFormularioDoBanco(id);

		return campoFormularioService.findAllByFormulario(formulario.getId());
	}

	public List<CampoRespostaModel> responderFormulario(PostBodyModel postBodyModel)
			throws BadRequestException, UnauthorizedException {

		FormularioModel formulario = formularioService.pegaFormularioDoBanco(postBodyModel.getIdFormulario());

		List<CampoFormularioModel> camposForm = campoFormularioService.findAllByFormulario(formulario.getId());

		List<ResponseModel> camposResposta = postBodyModel.getCamposResposta();


		if (camposForm.size() != camposResposta.size())
			throw new BadRequestException("Algum campo não foi enviado!");

		List<CampoRespostaModel> respostasTratadas = new ArrayList<>();

		RespostaModel novaResposta = new RespostaModel();
		novaResposta.setFormulario(formulario);
		novaResposta = respostaRepository.save(novaResposta);

		for (CampoFormularioModel campoFormularioModel : camposForm) {
			boolean achou = false;
			
				for (ResponseModel responseModel : camposResposta) {
				if (responseModel.getIdCampoFormulario().equals(campoFormularioModel.getId())) {
					CampoRespostaModel resposta = new CampoRespostaModel();
					resposta.setCampoFormulario(campoFormularioModel);
					resposta.setValor(responseModel.getResposta());
					resposta.setResposta(novaResposta);

					respostasTratadas.add(resposta);
					
					achou = true;
					break;
				}
			}
			if(!achou) {
				CampoRespostaModel resposta = new CampoRespostaModel();
				resposta.setCampoFormulario(campoFormularioModel);
				resposta.setValor("  ");
				resposta.setResposta(novaResposta);

				respostasTratadas.add(resposta);
			}
				

		}
		return campoRespostaRepository.saveAll(respostasTratadas);
	}

	public Page<CampoRespostaModel> getRespostas(Integer id, Integer page, UsuarioModel usuario) throws BadRequestException, UnauthorizedException {

		
		FormularioModel formulario = formularioService.pegaFormularioDoBanco(id);
		
		List<CampoFormularioModel> camposForm = campoFormularioService.findAllByFormulario(formulario.getId());
		
		Pageable pageable = Utils.setPageRequestConfig(page, camposForm.size());

		return campoRespostaRepository.findByIdAndUsuario(id, usuario.getId(), pageable);
	}

}
