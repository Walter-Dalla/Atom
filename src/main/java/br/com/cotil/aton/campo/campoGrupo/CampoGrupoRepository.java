package br.com.cotil.aton.campo.campoGrupo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampoGrupoRepository extends JpaRepository<CampoGrupoModel, Integer> {

  @Query("select cgm from  CampoGrupoModel cgm where "
      + " (:idCampo is null or cgm.campo.id = :idCampo) "
      + " and (:nomeCampo is null or cgm.campo.nome like %:nomeCampo%) "
      + " and (:descricaoCampo is null or cgm.campo.descricao like %:descricaoCampo%) "
      + " and (:idGrupo is null or cgm.grupo.id = :idGrupo) "
      + " and (:nomeGrupo is null or cgm.grupo.nome like %:nomeGrupo%) "
      + " and (:descricaoGrupo is null or cgm.grupo.descricao like %:descricaoGrupo%) "
      + " and cgm.grupo.id in (:idsDosGruoposDoUsuario) " + " and cgm.ativo = :ativo")
  Page<CampoGrupoModel> findByIdCampoAndNomeCampoAndDescricaoCampoAndIdGrupoAndNomeGrupoAndDescricaoGrupo(
      @Param("idCampo") Integer idCampo, @Param("nomeCampo") String nomeCampo,
      @Param("descricaoCampo") String descricaoCampo, @Param("idGrupo") Integer idGrupo,
      @Param("nomeGrupo") String nomeGrupo, @Param("descricaoGrupo") String descricaoGrupo,
      @Param("idsDosGruoposDoUsuario") List<Integer> idsDosGruoposDoUsuario,
      @Param("ativo") boolean ativo, Pageable pageable);

}
