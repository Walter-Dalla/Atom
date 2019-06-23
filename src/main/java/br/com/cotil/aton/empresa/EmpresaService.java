package br.com.cotil.aton.empresa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.userInfo.UserInfoModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.usuario.usuario.UsuarioRepository;

@Service
public class EmpresaService {
      
  private EmpresaRepository empresaRepository;
  private UsuarioRepository usuarioRepository;

  @Autowired
  public EmpresaService(EmpresaRepository empresaRepository) {
    this.empresaRepository = empresaRepository;
  }

  public Object getEmpresa(UsuarioModel userInfo) throws BadRequestException {


    Optional<EmpresaModel> empresaOptional = empresaRepository.findById(userInfo.getEmpresa().getId());

    if (!empresaOptional.isPresent())
      throw new BadRequestException(EmpresaConstantes.USUARIO_SEM_EMPRESA);

    return empresaOptional.get();
  }

  public Object createEmpresa(EmpresaModel empresa, UsuarioModel userInfo)
      throws BadRequestException, ForbiddenException {

    if (userInfo.getEmpresa().getId() != null)
      throw new ForbiddenException(EmpresaConstantes.USUARIO_COM_EMPRESA);

    empresa.setIdUsuarioCriador(userInfo.getId());

    return empresaRepository.save(empresa);
  }

  public Object updateEmpresa(EmpresaModel empresa, UserInfoModel userInfo)
      throws BadRequestException, ForbiddenException {

    if (userInfo.getIdEmpresa() == null)
      throw new ForbiddenException(EmpresaConstantes.USUARIO_SEM_EMPRESA);

    Optional<EmpresaModel> empresaOptional = empresaRepository.findById(userInfo.getIdEmpresa());

    if (!empresaOptional.isPresent())
      throw new BadRequestException(EmpresaConstantes.EMPRESA_INVALIDA);


    verifyIfNull(empresa, empresaOptional.get());

    empresa.setIdUsuarioCriador(userInfo.getIdUsuario());

    return empresaRepository.save(empresa);
  }

  public Object updateUsuarioCriador(EmpresaModel empresa, UsuarioModel userInfo)
      throws BadRequestException, ForbiddenException {

    if (userInfo.getEmpresa() == null)
      throw new ForbiddenException(EmpresaConstantes.USUARIO_SEM_EMPRESA);

    Optional<EmpresaModel> empresaOptional = empresaRepository.findById(userInfo.getEmpresa().getId());

    if (!empresaOptional.isPresent())
      throw new BadRequestException(EmpresaConstantes.EMPRESA_INVALIDA);

    Optional<UsuarioModel> usuarioNovoCriador =
        usuarioRepository.findById(empresa.getIdUsuarioCriador());

    if (!usuarioNovoCriador.isPresent())
      throw new BadRequestException(EmpresaConstantes.USUARIO_INEXISTENTE);

    empresa = empresaOptional.get();

    empresa.setIdUsuarioCriador(usuarioNovoCriador.get().getId());

    return empresaRepository.save(empresa);
  }

  private void verifyIfNull(EmpresaModel empresa, EmpresaModel empresaBanco)
      throws BadRequestException {
    empresa.setIdUsuarioCriador(empresaBanco.getIdUsuarioCriador());

    if (empresa.getRazaoSocial() == null)
      throw new BadRequestException(EmpresaConstantes.CAMPO_RAZAO_SOCIAL_INVALIDO);

    if (empresa.getEmail() == null)
      throw new BadRequestException(EmpresaConstantes.CAMPO_EMAIL_INVALIDO);
  }

}
