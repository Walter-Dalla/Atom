package br.com.cotil.aton.campo.customisado;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Repository
public interface CampoCustomizadoRepository extends JpaRepository<CampoCustomizadoModel, Integer> {

  Page<CampoCustomizadoModel> findAllByUsuario(UsuarioModel usuario, Pageable pageable);


  @Query("select ccm from CampoCustomizadoModel ccm where (ccm.id = :id or :id is null) "
      + " and (ccm.nome like %:nome% or :nome is null)"
      + " and (ccm.descricao like %:descricao% or :descricao is null) "
      + " and (ccm.ativo = :ativo) " 
      + " and (ccm.usuario.id = :idUsuario) "
      + " and (ccm.campoPadrao.ativo = 1)")
  Page<CampoCustomizadoModel> findWithFilter(@Param("id") Integer id, @Param("nome") String nome,
      @Param("descricao") String descricao, @Param("ativo") boolean ativo,
      @Param("idUsuario") Integer idUsuario, Pageable pageable);


  Optional<CampoCustomizadoModel> findByIdAndUsuario(Integer id, Integer idUsuario);



}
