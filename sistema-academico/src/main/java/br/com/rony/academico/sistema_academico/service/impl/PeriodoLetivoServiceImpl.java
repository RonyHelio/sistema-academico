package br.com.rony.academico.sistema_academico.service.impl;

import br.com.rony.academico.sistema_academico.dto.request.PeriodoLetivoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.PeriodoLetivoResponseDTO;
import br.com.rony.academico.sistema_academico.entity.PeriodoLetivo;
import br.com.rony.academico.sistema_academico.entity.Usuario;
import br.com.rony.academico.sistema_academico.mapper.PeriodoLetivoMapper;
import br.com.rony.academico.sistema_academico.repository.PeriodoLetivoRepository;
import br.com.rony.academico.sistema_academico.repository.UsuarioRepository;
import br.com.rony.academico.sistema_academico.service.PeriodoLetivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeriodoLetivoServiceImpl implements PeriodoLetivoService {

    private final PeriodoLetivoRepository periodoLetivoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public PeriodoLetivoResponseDTO salvar(PeriodoLetivoRequestDTO dto, Long usuarioId) {
        validarCoordenador(usuarioId);
        PeriodoLetivo entity = PeriodoLetivoMapper.toEntity(dto);
        PeriodoLetivo salvo = periodoLetivoRepository.save(entity);
        return PeriodoLetivoMapper.toDTO(salvo);
    }

    @Override
    public PeriodoLetivoResponseDTO buscarPorId(Long id) {
        PeriodoLetivo entity = periodoLetivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Período letivo não encontrado."));
        return PeriodoLetivoMapper.toDTO(entity);
    }

    @Override
    public List<PeriodoLetivoResponseDTO> listarTodos() {
        return periodoLetivoRepository.findAll()
                .stream()
                .map(PeriodoLetivoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PeriodoLetivoResponseDTO atualizar(Long id, PeriodoLetivoRequestDTO dto, Long usuarioId) {
        validarCoordenador(usuarioId);
        PeriodoLetivo entity = periodoLetivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Período letivo não encontrado."));

        entity.setAno(dto.getAno());
        entity.setSemestre(dto.getSemestre());
        entity.setDescricao(dto.getDescricao());

        PeriodoLetivo atualizado = periodoLetivoRepository.save(entity);
        return PeriodoLetivoMapper.toDTO(atualizado);
    }

    @Override
    public void inativar(Long id, Long usuarioId) {
        validarCoordenador(usuarioId);
        PeriodoLetivo entity = periodoLetivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Período letivo não encontrado."));
        entity.setStatus("I");
        periodoLetivoRepository.save(entity);
    }

    private void validarCoordenador(Long usuarioId) {
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (!"COORDENADOR".equals(solicitante.getPerfil())) {
            throw new RuntimeException("Acesso negado. Apenas coordenadores podem realizar esta operação.");
        }
    }
}
