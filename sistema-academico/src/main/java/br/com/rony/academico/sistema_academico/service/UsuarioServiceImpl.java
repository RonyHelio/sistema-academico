package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.UsuarioRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.UsuarioResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Usuario;
import br.com.rony.academico.sistema_academico.mapper.UsuarioMapper;
import br.com.rony.academico.sistema_academico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de gerenciamento de usuários.
 * Contém regras de negócio relacionadas a usuários do sistema.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(salvo);
    }

    @Override
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return UsuarioMapper.toDTO(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());
        usuario.setPerfil(dto.getPerfil());

        Usuario atualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(atualizado);
    }

    @Override
    public void inativar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        usuario.setStatus("I");
        usuarioRepository.save(usuario);
    }
}
