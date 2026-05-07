package br.com.rony.academico.sistema_academico.repository;

import br.com.rony.academico.sistema_academico.entity.ChatTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatTurmaRepository extends JpaRepository<ChatTurma, Long> {

    Optional<ChatTurma> findByTurmaId(Long turmaId);
}
