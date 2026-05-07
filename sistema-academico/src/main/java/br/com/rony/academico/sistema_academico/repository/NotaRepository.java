package br.com.rony.academico.sistema_academico.repository;

import br.com.rony.academico.sistema_academico.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByMatriculaTurmaId(Long matriculaTurmaId);

    List<Nota> findByMatriculaTurmaAlunoId(Long alunoId);
}
