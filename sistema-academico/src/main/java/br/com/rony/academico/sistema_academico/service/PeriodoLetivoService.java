package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.PeriodoLetivoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.PeriodoLetivoResponseDTO;

import java.util.List;

public interface PeriodoLetivoService {

    PeriodoLetivoResponseDTO salvar(PeriodoLetivoRequestDTO dto, Long usuarioId);

    PeriodoLetivoResponseDTO buscarPorId(Long id);

    List<PeriodoLetivoResponseDTO> listarTodos();

    PeriodoLetivoResponseDTO atualizar(Long id, PeriodoLetivoRequestDTO dto, Long usuarioId);

    void inativar(Long id, Long usuarioId);
}
