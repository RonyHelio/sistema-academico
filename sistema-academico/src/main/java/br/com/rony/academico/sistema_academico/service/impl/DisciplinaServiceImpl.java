package br.com.rony.academico.sistema_academico.service.impl;

import br.com.rony.academico.sistema_academico.dto.request.DisciplinaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.DisciplinaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Disciplina;
import br.com.rony.academico.sistema_academico.mapper.DisciplinaMapper;
import br.com.rony.academico.sistema_academico.repository.DisciplinaRepository;
import br.com.rony.academico.sistema_academico.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    @Override
    public DisciplinaResponseDTO salvar(DisciplinaRequestDTO dto) {
        Disciplina disciplina = DisciplinaMapper.toEntity(dto);
        Disciplina salva = disciplinaRepository.save(disciplina);
        return DisciplinaMapper.toDTO(salva);
    }

    @Override
    public DisciplinaResponseDTO buscarPorId(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));
        return DisciplinaMapper.toDTO(disciplina);
    }

    @Override
    public List<DisciplinaResponseDTO> listarTodas() {
        return disciplinaRepository.findAll()
                .stream()
                .map(DisciplinaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DisciplinaResponseDTO atualizar(Long id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));

        disciplina.setNome(dto.getNome());
        disciplina.setCodigo(dto.getCodigo());
        disciplina.setCodigoSuap(dto.getCodigoSuap());
        disciplina.setCargaHoraria(dto.getCargaHoraria());

        Disciplina atualizada = disciplinaRepository.save(disciplina);
        return DisciplinaMapper.toDTO(atualizada);
    }

    @Override
    public void inativar(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));
        disciplina.setStatus("I");
        disciplinaRepository.save(disciplina);
    }
}
