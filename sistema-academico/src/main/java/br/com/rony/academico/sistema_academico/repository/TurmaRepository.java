package br.com.rony.academico.sistema_academico.repository;

import br.com.rony.academico.sistema_academico.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    List<Turma> findByProfessorId(Long professorId);

    List<Turma> findByStatus(String status);
}
