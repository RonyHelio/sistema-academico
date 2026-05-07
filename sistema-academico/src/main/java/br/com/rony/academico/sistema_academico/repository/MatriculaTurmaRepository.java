package br.com.rony.academico.sistema_academico.repository;

import br.com.rony.academico.sistema_academico.entity.MatriculaTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaTurmaRepository extends JpaRepository<MatriculaTurma, Long> {

    List<MatriculaTurma> findByAlunoId(Long alunoId);

    List<MatriculaTurma> findByTurmaId(Long turmaId);
}
