package br.com.cotil.aton.formularios.campoCustomizado;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotil.aton.campo.customisado.CampoCustomizadoModel;
import br.com.cotil.aton.formularios.formulario.FormularioModel;

@Repository
public interface CampoFormularioRepository extends JpaRepository<CampoFormularioModel, Integer> {


  @Query("select cfm from CampoFormularioModel cfm where cfm.formulario.id = :idFormulario and cfm.ativo = 1")
  List<CampoFormularioModel> findAllByFormulario(@Param("idFormulario") Integer idFormulario);

  Optional<CampoFormularioModel> findOneByCampoAndFormulario(CampoCustomizadoModel campo,
      FormularioModel formulario);

}
