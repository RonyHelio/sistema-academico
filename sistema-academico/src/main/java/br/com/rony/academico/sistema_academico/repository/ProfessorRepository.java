package br.com.rony.academico.sistema_academico.repository;


import br.com.rony.academico.sistema_academico.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
