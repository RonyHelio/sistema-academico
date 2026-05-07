package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.FaltaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.FaltaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Falta;
import br.com.rony.academico.sistema_academico.entity.MatriculaTurma;
import br.com.rony.academico.sistema_academico.mapper.FaltaMapper;
import br.com.rony.academico.sistema_academico.repository.FaltaRepository;
import br.com.rony.academico.sistema_academico.repository.MatriculaTurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de gerenciamento de faltas.
 * Contém regras de negócio para lançamento de faltas.
 */
@Service
public class FaltaServiceImpl implements FaltaService {

    @Autowired
    private FaltaRepository faltaRepository;

    @Autowired
    private MatriculaTurmaRepository matriculaTurmaRepository;

    @Override
    public FaltaResponseDTO salvar(FaltaRequestDTO dto) {
        MatriculaTurma matricula = matriculaTurmaRepository.findById(dto.getMatriculaTurmaId())
                .orElseThrow(() -> new RuntimeException("Matrícula na turma não encontrada."));

        Falta falta = FaltaMapper.toEntity(dto, matricula);
        Falta salva = faltaRepository.save(falta);
        return FaltaMapper.toDTO(salva);
    }

    @Override
    public List<FaltaResponseDTO> listarPorMatriculaTurma(Long matriculaTurmaId) {
        return faltaRepository.findByMatriculaTurmaId(matriculaTurmaId)
                .stream()
                .map(FaltaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void inativar(Long id) {
        Falta falta = faltaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Falta não encontrada."));
        falta.setStatus("I");
        faltaRepository.save(falta);
    }
}
