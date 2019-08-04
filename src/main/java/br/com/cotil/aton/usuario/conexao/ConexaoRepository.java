package br.com.cotil.aton.usuario.conexao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConexaoRepository extends JpaRepository<ConexaoModel, Integer> {

  boolean existsByNomeUsuario(String nomeConexao);

  Optional<ConexaoModel> findByNomeUsuarioAndPassword(String nomeConexao, String pass);





}
