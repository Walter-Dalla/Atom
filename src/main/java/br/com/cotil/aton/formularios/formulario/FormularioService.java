package br.com.cotil.aton.formularios.formulario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class FormularioService {

	FormularioRepository formularioRepository;
	
	@Autowired
	public FormularioService(FormularioRepository formularioRepository) {
		super();
		this.formularioRepository = formularioRepository;
	}

	public List<FormularioModel> listaFormularios(UsuarioModel usuario, Integer id, String nomeFormulario) {
		// TODO Auto-generated method stub
		
		List<FormularioModel> lista = formularioRepository.findByIdAndNomeFormularioAndIdUsuario(id, nomeFormulario, usuario.getId());
		
		return lista;
	}

}
