package br.com.cotil.aton.grupo.grupoUsuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotil.aton.grupo.grupo.GrupoModel;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Repository
public interface GrupoUsuarioRepository extends JpaRepository<GrupoUsuarioModel, Integer> {

  List<GrupoUsuarioModel> findAllById(Integer idGrupo);

  @Query("select gum from GrupoUsuarioModel gum where gum.usuario.id = :usuarioId and gum.ativo = true and gum.grupo.ativo = true")
  List<GrupoUsuarioModel> findAllByUsuario(@Param("usuarioId") Integer id);

  @Query("select gum from GrupoUsuarioModel gum where gum.grupo.id = :grupoId and gum.ativo = true")
  List<GrupoUsuarioModel> findAllByGrupo(@Param("grupoId") Integer id);


  @Query("select gum from GrupoUsuarioModel gum where gum.grupo.nome like :grupoNome and gum.usuario.id = :idUsuario")
  Optional<GrupoUsuarioModel> findByUsuarioAndGrupoNome(@Param("idUsuario") Integer idUsuario,
      @Param("grupoNome") String nome);

  @Query("select gum from GrupoUsuarioModel gum where gum.grupo.id = :idGrupo "
      + " and gum.usuario.id = :idUsuario")
  Optional<GrupoUsuarioModel> getGrupousuarioByIdGrupoAndIdUsuario(
      @Param("idGrupo") Integer idGrupo, @Param("idUsuario") Integer idUsuario);

  void findByGrupoAndUsuario(UsuarioModel usuario, GrupoModel grupo);

  Optional<GrupoModel> findByGrupo(GrupoModel grupo);



}
