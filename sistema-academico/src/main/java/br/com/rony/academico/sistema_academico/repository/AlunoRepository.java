package br.com.rony.academico.sistema_academico.repository;


import br.com.rony.academico.sistema_academico.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
