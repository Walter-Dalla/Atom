package br.com.cotil.aton.campo.customisado;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Repository
public interface CampoCustomizadoRepository extends JpaRepository<CampoCustomizadoModel, Integer> {

  List<CampoCustomizadoModel> findAllByUsuario(UsuarioModel usuario);

  
  @Query("select ccm from CampoCustomizadoModel ccm where (ccm.id = :id or :id is null) " + 
      " and (ccm.nome like %:nome% or :nome is null) " + 
      " and (ccm.descricao like %:descricao% or :descricao is null) ")
  Optional<CampoCustomizadoModel> findByIdAndNomeAndDescricao(Integer id, String nome, String descricao);
  
  
  
  
}
