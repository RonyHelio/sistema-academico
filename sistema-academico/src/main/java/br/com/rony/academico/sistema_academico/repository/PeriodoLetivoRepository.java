package br.com.rony.academico.sistema_academico.repository;

import br.com.rony.academico.sistema_academico.entity.PeriodoLetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodoLetivoRepository extends JpaRepository<PeriodoLetivo, Long> {

    List<PeriodoLetivo> findByStatus(String status);
}
