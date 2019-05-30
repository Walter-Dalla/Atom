package br.com.cotil.aton.userInfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotil.aton.campo.grupoUsuario.GrupoUsuario;

@Repository
public interface UserInfoRepository extends JpaRepository<GrupoUsuario, Integer> {

  String selectUsuario = " usu.id, usu.perfilOrdem, ";

  String selectEmpresa = " emp.id, ";

  String selectGrupo = " gru.id ";

  @Query(value = "Select " + selectUsuario + selectEmpresa + selectGrupo
      + " from GrupoUsuario gruUsu join gruUsu.usuario usu "
      + " join usu.empresa emp join gruUsu.grupo gru where usu.id = :idUsuario ")
  public Optional<UserInfo> getUserInfo(@Param("idUsuario") Integer idUsuario);

}
