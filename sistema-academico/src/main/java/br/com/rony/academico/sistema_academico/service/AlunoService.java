package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.AlunoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.AlunoResponseDTO;
import br.com.rony.academico.sistema_academico.dto.response.FaltaResponseDTO;
import br.com.rony.academico.sistema_academico.dto.response.NotaResponseDTO;
import br.com.rony.academico.sistema_academico.dto.response.SituacaoAlunoTurmaDTO;
import br.com.rony.academico.sistema_academico.dto.response.TurmaResponseDTO;

import java.util.List;

public interface AlunoService {

    AlunoResponseDTO salvar(AlunoRequestDTO dto, Long usuarioId);

    AlunoResponseDTO buscarPorId(Long id);

    List<AlunoResponseDTO> listarTodos();

    AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto, Long usuarioId);

    void inativar(Long id, Long usuarioId);

    List<TurmaResponseDTO> listarTurmasDoAluno(Long alunoId, Long usuarioId);

    List<NotaResponseDTO> listarNotasDoAluno(Long alunoId, Long usuarioId);

    List<FaltaResponseDTO> listarFaltasDoAluno(Long alunoId, Long usuarioId);

    List<SituacaoAlunoTurmaDTO> consultarSituacao(Long alunoId, Long usuarioId);
}
