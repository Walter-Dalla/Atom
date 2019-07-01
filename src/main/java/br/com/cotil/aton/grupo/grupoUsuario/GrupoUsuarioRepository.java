package br.com.cotil.aton.grupo.grupoUsuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoUsuarioRepository extends JpaRepository<GrupoUsuarioModel, Integer> {

  List<GrupoUsuarioModel> findAllById(Integer idGrupo);

  @Query("select gum from GrupoUsuarioModel gum where gum.usuario.id = :usuarioId and gum.ativo = true")
  List<GrupoUsuarioModel> findAllByUsuario(@Param("usuarioId") Integer id);

  @Query("select gum from GrupoUsuarioModel gum where gum.grupo.id = :grupoId and gum.ativo = true")
  List<GrupoUsuarioModel> findAllByGrupo(@Param("grupoId") Integer id);



}
