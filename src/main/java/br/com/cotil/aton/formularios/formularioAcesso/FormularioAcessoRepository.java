package br.com.cotil.aton.formularios.formularioAcesso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioAcessoRepository extends JpaRepository<FormularioAcessoModel, Integer> {

  
  @Query("select fam from FormularioAcessoModel fam where (fam.formulario.id = :idFormulario or fam.formulario.usuario.id = :usuarioId) and fam.grupo.id in (:idsGrupo)")
  List<FormularioAcessoModel> pegaTodosOsAcessoDoFormularioDeAcordoComOGrupo(@Param("idFormulario") Integer idFormulario,
      @Param("idsGrupo") List<Integer> idsGrupo, @Param("usuarioId") Integer usuarioId);


  @Query("select fam from FormularioAcessoModel fam where fam.grupo.id in (:listaIdsGrupos)")
  List<FormularioAcessoModel> findAllByGrupoId(@Param("listaIdsGrupos") List<Integer> listaIdsGrupos);


}
