package br.com.cotil.aton.formularios.respostas.formularioResposta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CampoRespostaRepository extends JpaRepository<CampoRespostaModel, Integer> {

	@Query("select RM from CampoRespostaModel RM where RM.resposta.formulario.id = :idFormulario "
			+ " and RM.resposta.formulario.usuario.id = :idUsuario ")
	Page<CampoRespostaModel> findByIdAndUsuario(@Param("idFormulario") Integer id,
			@Param("idUsuario") Integer idUsuario, Pageable pageable);

}
