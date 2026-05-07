package br.com.rony.academico.sistema_academico.repository;

import br.com.rony.academico.sistema_academico.entity.MensagemChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemChatRepository extends JpaRepository<MensagemChat, Long> {

    List<MensagemChat> findByChatTurmaIdOrderByDataEnvioAsc(Long chatTurmaId);
}
