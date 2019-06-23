package br.com.cotil.aton.userInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cotil.aton.campo.grupoUsuario.GrupoUsuario;

@Repository
public interface UserInfoRepository extends JpaRepository<GrupoUsuario, Integer> {

}
