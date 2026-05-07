package br.com.rony.academico.sistema_academico.repository;

import br.com.rony.academico.sistema_academico.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByUsuarioId(Long usuarioId);

    List<Aluno> findByStatus(String status);
}
