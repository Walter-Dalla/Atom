package br.com.cotil.aton.formularios.formulario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoService;
import br.com.cotil.aton.formularios.campoFormulario.CampoFormualarioUtils;
import br.com.cotil.aton.formularios.campoFormulario.CampoFormularioModel;
import br.com.cotil.aton.formularios.campoFormulario.CampoFormularioRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

@Service
public class FormularioService {

	FormularioRepository formularioRepository;

	CampoFormularioRepository campoFormularioRepository;
	CampoCustomizadoService campoCustomizadoService;
	
	@Autowired
	public FormularioService(FormularioRepository formularioRepository,
			CampoFormularioRepository campoFormularioRepository, CampoCustomizadoService campoCustomizadoService) {
		super();
		this.formularioRepository = formularioRepository;
		this.campoFormularioRepository = campoFormularioRepository;
		this.campoCustomizadoService = campoCustomizadoService;
	}
	

	public Page<FormularioModel> listaFormularios(UsuarioModel usuario, Integer id, String nomeFormulario,
			boolean ativo, Integer page, Integer size) throws BadRequestException {
		
		if(!nomeFormulario.isEmpty())
			nomeFormulario = '%'+nomeFormulario+'%';
		
		Page<FormularioModel> lista = formularioRepository.findByIdAndNomeFormularioAndIdUsuarioAndAtivo(id,
				nomeFormulario, usuario.getId(), ativo, Utils.setPageRequestConfig(page, size));
		return lista;
	}
	
	public Page<FormularioModel> listaFormularios(Integer id, String nomeFormulario,
			boolean ativo, Integer page, Integer size) throws BadRequestException {

		return formularioRepository.findByIdAndNomeFormularioAndIdUsuarioAndAtivo(id,
				nomeFormulario, ativo, Utils.setPageRequestConfig(page, size));
	}


	public FormularioModel criaFormulario(UsuarioModel usuario, FormularioModel formulario)
			throws BadRequestException, ForbiddenException {

		validaFormulario(formulario, usuario);

		formulario.setUsuario(usuario);
		formulario.setAtivo(true);
		formulario.setPublicado(false);
		
		return formularioRepository.save(formulario);
	}

	public FormularioModel atualizaFormulario(UsuarioModel usuario, FormularioModel formulario)
			throws BadRequestException, ForbiddenException {

		FormularioModel formularioBanco = pegaFormularioDoBanco(formulario.getId(), usuario);

		validaFormulario(formularioBanco, usuario);

		formularioBanco.setNomeFormulario(formulario.getNomeFormulario());
		formularioBanco.setCompartilhavel(formulario.isCompartilhavel());
		
		return formularioRepository.save(formularioBanco);
	}

	public FormularioModel desabilitaFormulario(Integer idFormulario, UsuarioModel usuario)
			throws BadRequestException, ForbiddenException {

		FormularioModel formularioExistenteBD = pegaFormularioDoBanco(idFormulario, usuario);

		List<CampoFormularioModel> campoFormularioList = campoFormularioRepository.findAllByFormulario(idFormulario);

		for(CampoFormularioModel campoFormulario : campoFormularioList) {
			campoCustomizadoService.validaSeCampoExiste(campoFormulario.getCampo().getId(), usuario.getId());
		}

		campoFormularioRepository.deleteAllByIdFormulario(idFormulario);
		formularioRepository.deleteById(idFormulario);
		
		return formularioExistenteBD;
	}

	public FormularioModel pegaFormularioDoBanco(Integer idFormulario, UsuarioModel usuario)
			throws BadRequestException {

		Optional<FormularioModel> formularioOptional = formularioRepository.findByIdAndUsuario(idFormulario, usuario);

		if (!formularioOptional.isPresent())
			throw new BadRequestException("Formulario inexistente");

		return formularioOptional.get();
	}

	private void validaFormulario(FormularioModel formulario, UsuarioModel usuario)
			throws BadRequestException, ForbiddenException {
		if (Utils.isNullOrEmpty(formulario.getNomeFormulario()))
			throw new BadRequestException("Formulário precisa de nome!");
	}

	public FormularioModel pegaFormularioDoBanco(Integer id) throws BadRequestException, UnauthorizedException {
		Optional<FormularioModel> formularioOptional = formularioRepository.findById(id);

		if (!formularioOptional.isPresent())
			throw new BadRequestException("Formulario inexistente");

		CampoFormualarioUtils.validadarFormulario(formularioOptional.get());

		return formularioOptional.get();
	}


	public FormularioModel publicaFormulario(UsuarioModel usuario, Integer idFormulario)
			throws BadRequestException, ForbiddenException {

		FormularioModel formularioBanco = pegaFormularioDoBanco(idFormulario, usuario);

		formularioBanco.setPublicado(true);
		
		List<CampoFormularioModel> campoFormularioList = campoFormularioRepository.findAllByFormulario(idFormulario);

		for(CampoFormularioModel campoFormulario : campoFormularioList) {
			campoCustomizadoService.validaSeCampoExiste(campoFormulario.getCampo().getId(), usuario.getId());
			
			campoFormulario.setPublicado(true);
			
			campoFormularioRepository.save(campoFormulario);
		}
		
		
		return formularioRepository.save(formularioBanco);
	}
}
