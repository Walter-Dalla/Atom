package br.com.cotil.aton.campo.grupo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.userInfo.UserInfo;

@Service
public class GrupoService {

  @Autowired
  GrupoRepository grupoRepository;
  
  
  public List<GrupoModel> getGrupos(UserInfo userInfo) throws BadRequestException {
    
    if(userInfo.getIdGrupo() == null)
      throw new BadRequestException(GrupoConstantes.USUARIO_SEM_GRUPO);
    
    List<GrupoModel> grupoList = grupoRepository.findAllById(userInfo.getIdGrupo());
    
    if(!grupoList.isEmpty())
      throw new BadRequestException(GrupoConstantes.GRUPO_NÃO_EXISTE);
    
    return grupoList;
  }
  
  
  public void updateGrupos(GrupoModel grupo, UserInfo userInfo) {
    
    
    
  }
  
  
}
