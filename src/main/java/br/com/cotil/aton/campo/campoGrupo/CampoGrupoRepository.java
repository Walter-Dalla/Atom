package br.com.cotil.aton.campo.campoGrupo;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampoGrupoRepository extends JpaRepository<CampoGrupoModel, Integer> {

}
