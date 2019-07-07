package br.com.cotil.aton.campo.padrao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampoPadraoRepository extends JpaRepository<CampoPadraoModel, Integer> {



  @Query("select cpm from CampoPadraoModel cpm where (cpm.id = :id or :id is null) "
      + " and (cpm.nome like %:nome% or :nome is null) "
      + " and (cpm.descricao like %:descricao% or :descricao is null) ")
  Optional<CampoPadraoModel> findByIdAndNomeAndDescricao(@Param("id") Integer id,
      @Param("nome") String nome, @Param("descricao") String descricao);

  Optional<CampoPadraoModel> findById(Integer idCampoPadrao);



}
