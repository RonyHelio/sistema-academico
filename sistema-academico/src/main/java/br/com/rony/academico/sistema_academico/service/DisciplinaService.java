package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.DisciplinaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.DisciplinaResponseDTO;

import java.util.List;

public interface DisciplinaService {

    DisciplinaResponseDTO salvar(DisciplinaRequestDTO dto);

    DisciplinaResponseDTO buscarPorId(Long id);

    List<DisciplinaResponseDTO> listarTodas();

    DisciplinaResponseDTO atualizar(Long id, DisciplinaRequestDTO dto);

    void inativar(Long id);
}
