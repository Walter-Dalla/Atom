package br.com.cotil.aton.campo.grupo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoModel, Integer> {

  List<GrupoModel> findAllById(Integer idGrupo);



}
