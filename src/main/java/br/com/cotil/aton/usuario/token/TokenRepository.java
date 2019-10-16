package br.com.cotil.aton.usuario.token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cotil.aton.usuario.conexao.ConexaoModel;

@Repository
public interface TokenRepository extends JpaRepository<TokenModel, Integer> {

  @SuppressWarnings("unchecked")
  TokenModel save(TokenModel tokenAcesso);

  Optional<TokenModel> findByConexao(ConexaoModel conexaoModel);

}
