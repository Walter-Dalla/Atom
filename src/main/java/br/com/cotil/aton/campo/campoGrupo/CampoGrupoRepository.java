package br.com.cotil.aton.campo.campoGrupo;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampoGrupoRepository extends JpaRepository<CampoGrupoModel, Integer> {


  @Query("select cgm from CampoGrupoModel cgm where (cgm.grupo.id = :idGrupo or :idGrupo is null)"
      + " and (cgm.grupo.nomeGrupo like :nomeGrupo or :nomeGrupo is null) "
      + " and (cgm.grupo.descricao like :descricaoGrupo or :descricaoGrupo is null) "
      + " and (cgm.campo.id = :idCampo or :idCampo is null) "
      + " and (cgm.campo.nome like :nomeCampo or :nomeCampo is null) "
      + " and (cgm.campo.descricao like :descricaoCampo or :descricaoCampo is null) ")
  Page<CampoGrupoModel> findByIdCampoAndNomeCampoAndDescricaoCampoAndIdGrupoAndNomeGrupoAndDescricaoGrupo(
      @Param("idCampo") Integer idCampo, @Param("nomeCampo") String nomeCampo,
      @Param("descricaoCampo") String descricaoCampo, @Param("idGrupo") Integer idGrupo,
      @Param("nomeGrupo") String nomeGrupo, @Param("descricaoGrupo") String descricaoGrupo, Pageable pageable);



}
