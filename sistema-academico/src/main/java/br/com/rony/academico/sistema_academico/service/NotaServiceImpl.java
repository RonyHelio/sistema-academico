package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.NotaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.NotaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.MatriculaTurma;
import br.com.rony.academico.sistema_academico.entity.Nota;
import br.com.rony.academico.sistema_academico.mapper.NotaMapper;
import br.com.rony.academico.sistema_academico.repository.MatriculaTurmaRepository;
import br.com.rony.academico.sistema_academico.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de gerenciamento de notas.
 * Contém regras de negócio para lançamento de notas.
 */
@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;
    private final MatriculaTurmaRepository matriculaTurmaRepository;

    @Override
    public NotaResponseDTO salvar(NotaRequestDTO dto) {
        MatriculaTurma matricula = matriculaTurmaRepository.findById(dto.getMatriculaTurmaId())
                .orElseThrow(() -> new RuntimeException("Matrícula na turma não encontrada."));

        Nota nota = NotaMapper.toEntity(dto, matricula);
        Nota salva = notaRepository.save(nota);
        return NotaMapper.toDTO(salva);
    }

    @Override
    public List<NotaResponseDTO> listarPorMatriculaTurma(Long matriculaTurmaId) {
        return notaRepository.findByMatriculaTurmaId(matriculaTurmaId)
                .stream()
                .map(NotaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void inativar(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota não encontrada."));
        nota.setStatus("I");
        notaRepository.save(nota);
    }
}
