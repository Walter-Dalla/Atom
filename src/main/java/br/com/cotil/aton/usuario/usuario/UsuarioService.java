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

  public UsuarioModel getUser(String token, String ip) throws BadRequestException {

    return tokenService.getDadosToken(token, ip);
  }

  public Optional<UsuarioModel> getUser(Integer idUsuario) throws BadRequestException {

    return usuarioRepository.findById(idUsuario);
  }

  public UsuarioModel alterUser(String token, UsuarioModel usuarioAlterado, String ip)
      throws BadRequestException, UnauthorizedException {

    UsuarioModel usuario = tokenService.getDadosToken(token, ip);

    if (usuarioAlterado.getId() != usuario.getId())
      throw new UnauthorizedException("Sem autorização para alterar outro usuario");

    usuarioAlterado.setId(usuario.getId());

    return usuarioRepository.saveAndFlush(usuarioAlterado);
  }

  public UsuarioModel desativarUsuario(String token, String ip) throws BadRequestException {

    UsuarioModel usuario = tokenService.getDadosToken(token, ip);

    usuario.setAtivo(false);

    return usuarioRepository.saveAndFlush(usuario);
  }
}
