package br.com.cotil.aton.formularios.formulario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
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

	public List<FormularioModel> listaFormularios(UsuarioModel usuario, Integer id, String nomeFormulario, boolean ativo) {

		List<FormularioModel> lista = formularioRepository.findByIdAndNomeFormularioAndIdUsuarioAndAtivo(id, nomeFormulario,
				usuario.getId(), ativo);

		return lista;
	}

	public FormularioModel criaFormulario(UsuarioModel usuario, FormularioModel formulario) throws BadRequestException {

		if (Utils.isNullOrEmpty(formulario.getNomeFormulario()))
			throw new BadRequestException("Formulário precisa de nome!");

		formulario.setUsuario(usuario);
		formulario.setAtivo(true);

		return formularioRepository.save(formulario);
	}

	public FormularioModel atualizaFormulario(UsuarioModel usuario, FormularioModel formulario)
			throws BadRequestException, ForbiddenException {

		Optional<FormularioModel> formularioOptional = formularioRepository.findById(formulario.getId());

		if (!formularioOptional.isPresent())
			throw new BadRequestException("Formulário Inexistente!");

		FormularioModel formularioExistenteBD = formularioOptional.get();

		if (formularioExistenteBD.getUsuario().getId() != usuario.getId())
			throw new ForbiddenException("Usuário Inválido!");

		formularioExistenteBD.setNomeFormulario(formulario.getNomeFormulario());
		formularioExistenteBD.setCompartilhavel(formulario.isCompartilhavel());

		return formularioRepository.save(formularioExistenteBD);
	}

	public FormularioModel desabilitaFormulario(Integer idFormulario, UsuarioModel usuario) throws BadRequestException, ForbiddenException {
		
		Optional<FormularioModel> formularioOptional = formularioRepository.findById(idFormulario);
		
		if (!formularioOptional.isPresent())
			throw new BadRequestException("Formulário Inexistente!");
		
		FormularioModel formularioExistenteBD = formularioOptional.get();
		
		if (formularioExistenteBD.getUsuario().getId() != usuario.getId())
			throw new ForbiddenException("Usuário Inválido!");

		formularioExistenteBD.setAtivo(false);
		
		return formularioRepository.save(formularioExistenteBD);
	}

}
