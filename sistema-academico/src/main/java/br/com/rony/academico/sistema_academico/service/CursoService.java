package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.CursoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.CursoResponseDTO;

import java.util.List;

/**
 * Interface de serviço para gerenciamento de cursos.
 */
public interface CursoService {

    CursoResponseDTO salvar(CursoRequestDTO dto);

    CursoResponseDTO buscarPorId(Long id);

    List<CursoResponseDTO> listarTodos();

    CursoResponseDTO atualizar(Long id, CursoRequestDTO dto);

    void inativar(Long id);
}
