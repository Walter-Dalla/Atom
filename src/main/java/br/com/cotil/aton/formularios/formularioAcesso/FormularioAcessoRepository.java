package br.com.cotil.aton.formularios.formularioAcesso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioAcessoRepository extends JpaRepository<FormularioAcessoModel, Integer> {

  
  @Query("select fam from FormularioAcessoModel fam where fam.formulario.id = :idFormulario and fam.grupo.id in :idsGrupo")
  List<FormularioAcessoModel> findAllByIdFormularioAndIdsGrupo(@Param("idFormulario") Integer idFormulario,
      @Param("idsGrupo") List<Integer> idsGrupo);


}
