package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.FaltaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.FaltaResponseDTO;

import java.util.List;

public interface FaltaService {

    FaltaResponseDTO salvar(FaltaRequestDTO dto, Long usuarioId);

    List<FaltaResponseDTO> listarPorMatriculaTurma(Long matriculaTurmaId);

    void inativar(Long id, Long usuarioId);
}
