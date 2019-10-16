package br.com.cotil.aton.formularios.campoFormulario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampoFormularioRepository extends JpaRepository<CampoFormularioModel, Integer> {


  @Query("select cfm from CampoFormularioModel cfm where cfm.formulario.id = :idFormulario and cfm.ativo = 1")
  List<CampoFormularioModel> findAllByFormulario(@Param("idFormulario") Integer idFormulario);

}
