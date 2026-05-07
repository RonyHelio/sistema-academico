package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.NotaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.NotaResponseDTO;

import java.util.List;

/**
 * Interface de serviço para gerenciamento de notas.
 */
public interface NotaService {

    NotaResponseDTO salvar(NotaRequestDTO dto);

    List<NotaResponseDTO> listarPorMatriculaTurma(Long matriculaTurmaId);

    void inativar(Long id);
}
