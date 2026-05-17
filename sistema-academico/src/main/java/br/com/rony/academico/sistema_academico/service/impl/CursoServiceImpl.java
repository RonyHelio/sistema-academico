package br.com.rony.academico.sistema_academico.service.impl;

import br.com.rony.academico.sistema_academico.dto.request.CursoRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.CursoResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Curso;
import br.com.rony.academico.sistema_academico.entity.Usuario;
import br.com.rony.academico.sistema_academico.mapper.CursoMapper;
import br.com.rony.academico.sistema_academico.repository.CursoRepository;
import br.com.rony.academico.sistema_academico.repository.UsuarioRepository;
import br.com.rony.academico.sistema_academico.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public CursoResponseDTO salvar(CursoRequestDTO dto, Long usuarioId) {
        validarCoordenador(usuarioId);
        Curso curso = CursoMapper.toEntity(dto);
        Curso salvo = cursoRepository.save(curso);
        return CursoMapper.toDTO(salvo);
    }

    @Override
    public CursoResponseDTO buscarPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));
        return CursoMapper.toDTO(curso);
    }

    @Override
    public List<CursoResponseDTO> listarTodos() {
        return cursoRepository.findAll()
                .stream()
                .map(CursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CursoResponseDTO atualizar(Long id, CursoRequestDTO dto, Long usuarioId) {
        validarCoordenador(usuarioId);
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));

        curso.setNome(dto.getNome());
        curso.setCodigoSuap(dto.getCodigoSuap());

        Curso atualizado = cursoRepository.save(curso);
        return CursoMapper.toDTO(atualizado);
    }

    @Override
    public void inativar(Long id, Long usuarioId) {
        validarCoordenador(usuarioId);
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));
        curso.setStatus("I");
        cursoRepository.save(curso);
    }

    private void validarCoordenador(Long usuarioId) {
        Usuario solicitante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (!"COORDENADOR".equals(solicitante.getPerfil())) {
            throw new RuntimeException("Acesso negado. Apenas coordenadores podem realizar esta operação.");
        }
    }
}
