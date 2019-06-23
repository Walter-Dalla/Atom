package br.com.cotil.aton.userInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.usuario.token.TokenService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Service
public class UserInfoService {

  UserInfoRepository userInfoRepository;

  TokenService tokenService;
  
  @Autowired
  UserInfoService(UserInfoRepository userInfoRepository, TokenService tokenService) {
    this.userInfoRepository = userInfoRepository;
    this.tokenService = tokenService;
  }

  public UsuarioModel getUserInfo(String token) throws ForbiddenException, BadRequestException {

    UsuarioModel usuarioModel = tokenService.getDadosToken(token);
    
    
    
    
    return usuarioModel;
  }

  
  
}
