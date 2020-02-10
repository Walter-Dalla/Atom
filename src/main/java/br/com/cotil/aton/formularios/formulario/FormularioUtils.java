package br.com.cotil.aton.formularios.formulario;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.formularios.campoFormulario.CampoFormularioUtils;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.ErroUtils;
import br.com.cotil.aton.util.OptionalUtils;
import br.com.cotil.aton.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FormularioUtils {

    FormularioRepository formularioRepository;

    public FormularioUtils(FormularioRepository formularioRepository){
        this.formularioRepository = formularioRepository;
    }

    public FormularioModel pegaFormularioDoBanco(Integer idFormulario, UsuarioModel usuario)
            throws BadRequestException {

        Optional<FormularioModel> formularioOptional = formularioRepository.findByIdAndUsuario(idFormulario, usuario);

        OptionalUtils.validIfIsNotPresent(formularioOptional, new BadRequestException("Formulario inexistente"));

        return formularioOptional.get();
    }

    public void validaFormulario(FormularioModel formulario, UsuarioModel usuario)
            throws BadRequestException, ForbiddenException {

        List<String> listaErro = new ArrayList<>();

        if(formulario.getUsuario() != null) {
            if(formulario.getUsuario().getId() != usuario.getId())
                listaErro.add("Você não tem acesso a esse formulario");
        }else{
            listaErro.add("O formulario presisa de um usuario!");
        }
        if (Utils.isNullOrEmpty(formulario.getNomeFormulario()))
            listaErro.add("Formulário precisa de nome!");

        ErroUtils.validaErroList(listaErro);
    }

    public void validaFormulario(FormularioModel formulario, UsuarioModel usuario, List<String> listaErro)
            throws BadRequestException, ForbiddenException {
        if(formulario.getUsuario() == null) {
            if(formulario.getUsuario().getId() != usuario.getId())
                listaErro.add("Você não tem acesso a esse formulario");
        }else{
            listaErro.add("O formulario presisa de um usuario!");
        }
        if (Utils.isNullOrEmpty(formulario.getNomeFormulario()))
            listaErro.add("Formulário precisa de nome!");
    }

    public FormularioModel pegaFormularioDoBanco(Integer id) throws BadRequestException, UnauthorizedException {
        Optional<FormularioModel> formularioOptional = formularioRepository.findById(id);

        OptionalUtils.validIfIsNotPresent(formularioOptional, new BadRequestException("Formulario inexistente"));

        CampoFormularioUtils.validadarFormulario(formularioOptional.get());

        return formularioOptional.get();
    }

}
