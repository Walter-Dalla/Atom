package br.com.cotil.aton.usuario.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.UnauthorizedException;
import br.com.cotil.aton.usuario.token.TokenService;

@Service
public class UsuarioService {

  TokenService tokenService;
  UsuarioRepository usuarioRepository;


  @Autowired
  public UsuarioService(TokenService tokenService, UsuarioRepository usuarioRepository) {
    super();
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
  }

  public Optional<UsuarioModel> getUsuarioById(Integer idUsuario) throws BadRequestException {

    return usuarioRepository.findById(idUsuario);
  }

  public UsuarioModel alterUser(String token, UsuarioModel usuarioAlterado, UsuarioModel usuario)
      throws BadRequestException, UnauthorizedException {

    usuario.setEmail(usuarioAlterado.getEmail());
    usuario.setNome(usuarioAlterado.getNome());

    return usuarioRepository.saveAndFlush(usuario);
  }

  public UsuarioModel desativarUsuario(String token, String ip) throws BadRequestException {

    UsuarioModel usuario = tokenService.getUsuarioByToken(token, ip);

    usuario.setAtivo(false);

    return usuarioRepository.saveAndFlush(usuario);
  }
}
