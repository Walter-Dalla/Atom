package br.com.cotil.aton.formularios.formulario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotil.aton.usuario.usuario.UsuarioModel;

@Repository
public interface FormularioRepository extends JpaRepository<FormularioModel, Integer> {

	@Query("select fm from FormularioModel fm where (fm.nomeFormulario like :nomeFormulario or :nomeFormulario is null)"
			+ " and (fm.usuario.id = :idUsuario)"
			+ " and (fm.id = :id or :id is null)"
			+ " and (fm.ativo = :ativo or :ativo is null)")
  Page<FormularioModel> findByIdAndNomeFormularioAndIdUsuarioAndAtivo(@Param("id") Integer id,
			@Param("nomeFormulario") String nomeFormulario, @Param("idUsuario") Integer idUsuario,
			@Param("ativo") boolean ativo, Pageable pageable);
	
	@Query("select fm from FormularioModel fm where (fm.nomeFormulario like :nomeFormulario or :nomeFormulario is null)"
			+ " and (fm.id = :id)"
			+ " and (fm.ativo = :ativo or :ativo is null)")
  Page<FormularioModel> findByIdAndNomeFormularioAndIdUsuarioAndAtivo(@Param("id") Integer id,
			@Param("nomeFormulario") String nomeFormulario,
			@Param("ativo") boolean ativo, Pageable pageable);

  Optional<FormularioModel> findByIdAndUsuario(Integer idFormulario, UsuarioModel usuario);

}
