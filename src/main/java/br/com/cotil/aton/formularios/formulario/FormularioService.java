package br.com.cotil.aton.formularios.formulario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.formularios.campoFormulario.CampoFormualarioUtils;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

@Service
public class FormularioService {

	FormularioRepository formularioRepository;

	@Autowired
	public FormularioService(FormularioRepository formularioRepository) {
		super();
		this.formularioRepository = formularioRepository;
	}

	public Page<FormularioModel> listaFormularios(UsuarioModel usuario, Integer id, String nomeFormulario,
			boolean ativo, Integer page, Integer size) {

		Page<FormularioModel> lista = formularioRepository.findByIdAndNomeFormularioAndIdUsuarioAndAtivo(id,
				nomeFormulario, usuario.getId(), ativo, Utils.setPageRequestConfig(page, size));

		return lista;
	}

	public FormularioModel criaFormulario(UsuarioModel usuario, FormularioModel formulario)
			throws BadRequestException, ForbiddenException {

		validaFormulario(formulario, usuario);

		formulario.setUsuario(usuario);
		formulario.setAtivo(true);

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

		formularioExistenteBD.setAtivo(false);

		return formularioRepository.save(formularioExistenteBD);
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

}
