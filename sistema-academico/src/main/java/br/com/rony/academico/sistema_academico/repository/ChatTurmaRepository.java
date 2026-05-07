package br.com.rony.academico.sistema_academico.repository;


import br.com.rony.academico.sistema_academico.model.ChatTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatTurmaRepository extends JpaRepository<ChatTurma, Long> {

}
