package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.AlunoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.AlunoResponseDTO;

import java.util.List;

/**
 * Interface de serviço para gerenciamento de alunos.
 */
public interface AlunoService {

    AlunoResponseDTO salvar(AlunoRequestDTO dto);

    AlunoResponseDTO buscarPorId(Long id);

    List<AlunoResponseDTO> listarTodos();

    AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto);

    void inativar(Long id);
}
