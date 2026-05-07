package br.com.rony.academico.sistema_academico.repository;

import br.com.rony.academico.sistema_academico.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByUsuarioId(Long usuarioId);
}
