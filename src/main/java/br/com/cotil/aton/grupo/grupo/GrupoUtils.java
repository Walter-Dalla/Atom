package br.com.cotil.aton.grupo.grupo;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

public class GrupoUtils {

  public static GrupoModel validarGrupo(GrupoModel grupo) throws BadRequestException {

    if (Utils.isNullOrEmpty(grupo.getNome()))
      throw new BadRequestException("O Grupo necessista ter um nome");

    return grupo;
  }

  public static GrupoModel prepararGrupo(GrupoModel grupo, UsuarioModel usuario) {

    grupo.setAtivo(true);

    grupo.setUsuario(usuario);

    return grupo;
  }

  public static GrupoModel matchGrupos(GrupoModel grupoNoBanco, GrupoModel grupo) {
    grupoNoBanco.setNome(grupo.getNome());
    grupoNoBanco.setDescricao(grupo.getDescricao());

    return grupoNoBanco;
  }


}
