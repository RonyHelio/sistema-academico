package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.MatriculaTurmaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.MatriculaTurmaResponseDTO;

import java.util.List;

public interface MatriculaTurmaService {

    MatriculaTurmaResponseDTO salvar(MatriculaTurmaRequestDTO dto, Long usuarioId);

    MatriculaTurmaResponseDTO buscarPorId(Long id);

    List<MatriculaTurmaResponseDTO> listarPorAluno(Long alunoId);

    List<MatriculaTurmaResponseDTO> listarPorTurma(Long turmaId);

    void inativar(Long id, Long usuarioId);
}
