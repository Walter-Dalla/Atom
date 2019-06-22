package br.com.cotil.aton.usuario.conexao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConexaoRepository extends JpaRepository<ConexaoModel, Integer> {

  boolean existsByNomeConexao(String nomeConexao);

  Optional<ConexaoModel> findByNomeConexaoAndPass(String nomeConexao, String pass);





}
