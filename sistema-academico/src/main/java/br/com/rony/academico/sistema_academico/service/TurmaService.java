package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.TurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;

import java.util.List;

/**
 * Interface de serviço para gerenciamento de turmas.
 */
public interface TurmaService {

    TurmaResponseDTO salvar(TurmaRequestDTO dto);

    TurmaResponseDTO buscarPorId(Long id);

    List<TurmaResponseDTO> listarTodas();

    TurmaResponseDTO atualizar(Long id, TurmaRequestDTO dto);

    void inativar(Long id);
}
