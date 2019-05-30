package br.com.cotil.aton.userInfo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.ForbiddenException;

@Service
public class AutenticadorUtils {

  UserInfoRepository userInfoRepository;

  @Autowired
  AutenticadorUtils(UserInfoRepository userInfoRepository) {
    this.userInfoRepository = userInfoRepository;
  }

  public UserInfo getUserInfo(String ks) throws ForbiddenException {

    Optional<UserInfo> userInfoOptional = userInfoRepository.getUserInfo(Integer.getInteger(ks));

    if (!userInfoOptional.isPresent())
      throw new ForbiddenException("Usuario não logado");

    return userInfoOptional.get();
  }

}
