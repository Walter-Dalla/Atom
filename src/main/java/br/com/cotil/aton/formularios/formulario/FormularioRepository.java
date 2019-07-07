package br.com.cotil.aton.formularios.formulario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioRepository extends JpaRepository<FormularioModel, Integer> {

	@Query("select fm from FormularioModel fm where (fm.nomeFormulario like :nomeFormulario or :nomeFormulario is null)"
			+ " and (fm.usuario.id = :idUsuario)"
			+ " and (fm.id = :id or :id is null)")
	List<FormularioModel> findByIdAndNomeFormularioAndIdUsuario(@Param("id") Integer id,
			@Param("nomeFormulario") String nomeFormulario, @Param("idUsuario") Integer idUsuario);

}
