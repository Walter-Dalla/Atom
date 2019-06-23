package br.com.cotil.aton.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.userInfo.UserInfoService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.userInfo.UserInfoModel;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {


  EmpresaRepository empresaRepository;
  EmpresaService empresaService;
  UserInfoService UserInfoService;

  @Autowired
  public EmpresaController(UserInfoService UserInfoService, EmpresaService empresaService) {
    this.UserInfoService = UserInfoService;
    this.empresaService = empresaService;
  }

  @GetMapping
  public Object getEmpresa(@RequestHeader("Key") String key)
      throws ForbiddenException, BadRequestException {

    UsuarioModel userInfo = UserInfoService.getUserInfo(key);


    return empresaService.getEmpresa(userInfo);
  }

  @PostMapping
  public Object createEmpresa(@RequestHeader("Key") String key, @RequestBody EmpresaModel empresa)
      throws ForbiddenException, BadRequestException {

    UsuarioModel userInfo = UserInfoService.getUserInfo(key);


    return empresaService.createEmpresa(empresa, userInfo);
  }
  
  @PutMapping
  public Object updateUsuarioCriador(@RequestHeader("Key") String key, @RequestBody EmpresaModel empresa)
      throws ForbiddenException, BadRequestException {

    UsuarioModel userInfo = UserInfoService.getUserInfo(key);


    return empresaService.updateUsuarioCriador(empresa, userInfo);
  }



}
