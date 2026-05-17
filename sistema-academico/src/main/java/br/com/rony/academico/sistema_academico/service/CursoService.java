package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.CursoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.CursoResponseDTO;

import java.util.List;

public interface CursoService {

    CursoResponseDTO salvar(CursoRequestDTO dto, Long usuarioId);

    CursoResponseDTO buscarPorId(Long id);

    List<CursoResponseDTO> listarTodos();

    CursoResponseDTO atualizar(Long id, CursoRequestDTO dto, Long usuarioId);

    void inativar(Long id, Long usuarioId);
}
