package br.com.cotil.aton.userInfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@RestController
@RequestMapping("/user/info")
public class UserInfoController {

  UserInfoService userInfoService;



  public UserInfoController(UserInfoService userInfoService) {
    super();
    this.userInfoService = userInfoService;
  }



  @GetMapping
  public UsuarioModel getUserInfo(@RequestHeader("Token") String token)
      throws ForbiddenException, BadRequestException {

    return userInfoService.getUserInfo(token);
  }


}
