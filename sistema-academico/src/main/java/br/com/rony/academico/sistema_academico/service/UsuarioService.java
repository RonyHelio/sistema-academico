package br.com.rony.academico.sistema_academico.service;

import br.com.rony.academico.sistema_academico.dto.request.UsuarioRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.UsuarioResponseDTO;

import java.util.List;

/**
 * Interface de serviço para gerenciamento de usuários.
 */
public interface UsuarioService {

    UsuarioResponseDTO salvar(UsuarioRequestDTO dto);

    UsuarioResponseDTO buscarPorId(Long id);

    List<UsuarioResponseDTO> listarTodos();

    UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto);

    void inativar(Long id);
}
