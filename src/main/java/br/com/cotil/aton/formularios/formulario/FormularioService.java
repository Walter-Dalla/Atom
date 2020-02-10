package br.com.cotil.aton.formularios.formulario;

import java.util.List;


import br.com.cotil.aton.campo.customisado.CampoCustomizadoRepository;
import br.com.cotil.aton.campo.customisado.CampoCustomizadoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.formularios.campoFormulario.CampoFormularioModel;
import br.com.cotil.aton.formularios.campoFormulario.CampoFormularioRepository;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

@Service
public class FormularioService {

	private FormularioRepository formularioRepository;
	private CampoFormularioRepository campoFormularioRepository;
	private CampoCustomizadoUtils campoCustomizadoUtils;
	private FormularioUtils formularioUtils;

	@Autowired
	public FormularioService(FormularioRepository formularioRepository,
			CampoFormularioRepository campoFormularioRepository,
				 CampoCustomizadoRepository campoCustomizadoRepository) {
		super();
		this.formularioRepository = formularioRepository;
		this.campoFormularioRepository = campoFormularioRepository;
		this.campoCustomizadoUtils = new CampoCustomizadoUtils(campoCustomizadoRepository);
		this.formularioUtils = new FormularioUtils(formularioRepository);
	}

	/**
	 * Metodo GET
	 *
	 */
	public Page<FormularioModel> listaFormularios(UsuarioModel usuario, Integer id, String nomeFormulario,
			boolean ativo, Integer page, Integer size) throws BadRequestException {
		
		if(!Utils.isNullOrEmpty(nomeFormulario))
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

	/**
	 * Metodo Post
	 *
	 */
	public FormularioModel criaFormulario(UsuarioModel usuario, FormularioModel formulario)
			throws BadRequestException, ForbiddenException {


		formulario.setUsuario(usuario);
		formulario.setAtivo(true);
		formulario.setPublicado(false);

		formularioUtils.validaFormulario(formulario, usuario);

		return formularioRepository.save(formulario);
	}


	/**
	 * Metodo Patch
	 *
	 */
	public FormularioModel atualizaFormulario(UsuarioModel usuario, FormularioModel formulario)
			throws BadRequestException, ForbiddenException {

		FormularioModel formularioBanco = formularioUtils.pegaFormularioDoBanco(formulario.getId(), usuario);

		formularioUtils.validaFormulario(formularioBanco, usuario);

		formularioBanco.setNomeFormulario(formulario.getNomeFormulario());
		formularioBanco.setCompartilhavel(formulario.isCompartilhavel());
		
		return formularioRepository.save(formularioBanco);
	}

	/**
	 * Metodo Delete
	 *
	 */
	public FormularioModel desabilitaFormulario(Integer idFormulario, UsuarioModel usuario)
			throws BadRequestException, ForbiddenException {

		FormularioModel formularioExistenteBD = formularioUtils.pegaFormularioDoBanco(idFormulario, usuario);

		List<CampoFormularioModel> campoFormularioList = campoFormularioRepository.findAllByFormulario(idFormulario);

		for(CampoFormularioModel campoFormulario : campoFormularioList) {
			campoCustomizadoUtils.validaSeCampoExiste(campoFormulario.getCampo().getId(), usuario.getId());
		}

		campoFormularioRepository.deleteAllByIdFormulario(idFormulario);
		formularioRepository.deleteById(idFormulario);
		
		return formularioExistenteBD;
	}

	/**
	 * Metodo Patch
	 *
	 */
	public FormularioModel publicaFormulario(UsuarioModel usuario, Integer idFormulario)
			throws BadRequestException, ForbiddenException {

		FormularioModel formularioBanco = formularioUtils.pegaFormularioDoBanco(idFormulario, usuario);

		formularioBanco.setPublicado(true);
		
		List<CampoFormularioModel> campoFormularioList = campoFormularioRepository.findAllByFormulario(idFormulario);

		for(CampoFormularioModel campoFormulario : campoFormularioList) {
			campoCustomizadoUtils.validaSeCampoExiste(campoFormulario.getCampo().getId(), usuario.getId());
			
			campoFormulario.setPublicado(true);
			
			campoFormularioRepository.save(campoFormulario);
		}
		
		
		return formularioRepository.save(formularioBanco);
	}
}
