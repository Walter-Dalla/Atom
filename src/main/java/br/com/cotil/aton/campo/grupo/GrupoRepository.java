package br.com.cotil.aton.campo.grupo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cotil.aton.empresa.EmpresaModel;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoModel, Integer> {

  Optional<EmpresaModel> findEmpresaById(Integer idGrupo);

  List<GrupoModel> findAllById(Integer idGrupo);



}
