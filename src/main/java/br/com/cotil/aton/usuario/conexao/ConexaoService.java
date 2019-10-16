package br.com.cotil.aton.usuario.conexao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ConflictException;

@Service
public class ConexaoService {

  ConexaoRepository conexaoRepository;

  @Autowired
  public ConexaoService(ConexaoRepository conexaoRepository) {
    super();
    this.conexaoRepository = conexaoRepository;
  }

  public ConexaoModel validarConexao(ConexaoModel conexao)
      throws ConflictException, BadRequestException {

    Optional<ConexaoModel> conexaoOpcional = conexaoRepository
        .findByNomeUsuarioAndPassword(conexao.getNomeUsuario(), conexao.getPassword());

    if (!conexaoOpcional.isPresent())
      throw new BadRequestException("Usuario não encontrado");

    return conexaoOpcional.get();
  }

  public ConexaoModel validarConexao(Integer idConexao)
      throws BadRequestException {

    Optional<ConexaoModel> conexaoOpcional = conexaoRepository.findById(idConexao);

    if (!conexaoOpcional.isPresent())
      throw new BadRequestException("Usuario não encontrado");

    return conexaoOpcional.get();
  }
}
