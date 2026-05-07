package br.com.rony.academico.sistema_academico.repository;

import br.com.rony.academico.sistema_academico.entity.Falta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaltaRepository extends JpaRepository<Falta, Long> {

    List<Falta> findByMatriculaTurmaId(Long matriculaTurmaId);

    List<Falta> findByMatriculaTurmaAlunoId(Long alunoId);
}
