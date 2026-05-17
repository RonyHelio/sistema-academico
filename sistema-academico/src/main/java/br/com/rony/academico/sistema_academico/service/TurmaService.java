package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.TurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;

import java.util.List;

public interface TurmaService {

    TurmaResponseDTO salvar(TurmaRequestDTO dto, Long usuarioId);

    TurmaResponseDTO buscarPorId(Long id);

    List<TurmaResponseDTO> listarTodas();

    TurmaResponseDTO atualizar(Long id, TurmaRequestDTO dto, Long usuarioId);

    void inativar(Long id, Long usuarioId);
}
