package br.com.rony.academico.sistema_academico.service.impl;

import br.com.rony.academico.sistema_academico.dto.request.FaltaRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.FaltaResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Falta;
import br.com.rony.academico.sistema_academico.entity.MatriculaTurma;
import br.com.rony.academico.sistema_academico.entity.Usuario;
import br.com.rony.academico.sistema_academico.mapper.FaltaMapper;
import br.com.rony.academico.sistema_academico.repository.FaltaRepository;
import br.com.rony.academico.sistema_academico.repository.MatriculaTurmaRepository;
import br.com.rony.academico.sistema_academico.repository.UsuarioRepository;
import br.com.rony.academico.sistema_academico.service.FaltaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaltaServiceImpl implements FaltaService {

    private final FaltaRepository faltaRepository;
    private final MatriculaTurmaRepository matriculaTurmaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public FaltaResponseDTO salvar(FaltaRequestDTO dto, Long usuarioId) {
        validarProfessorOuCoordenador(usuarioId);

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
    public void inativar(Long id, Long usuarioId) {
        validarProfessorOuCoordenador(usuarioId);
        Falta falta = faltaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Falta não encontrada."));
        falta.setStatus("I");
        faltaRepository.save(falta);
    }

    private void validarProfessorOuCoordenador(Long usuarioId) {
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (!"PROFESSOR".equals(solicitante.getPerfil()) && !"COORDENADOR".equals(solicitante.getPerfil())) {
            throw new RuntimeException("Acesso negado. Apenas professores ou coordenadores podem realizar esta operação.");
        }
    }
}
