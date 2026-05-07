package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.FaltaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.FaltaResponseDTO;

import java.util.List;

/**
 * Interface de serviço para gerenciamento de faltas.
 */
public interface FaltaService {

    FaltaResponseDTO salvar(FaltaRequestDTO dto);

    List<FaltaResponseDTO> listarPorMatriculaTurma(Long matriculaTurmaId);

    void inativar(Long id);
}
