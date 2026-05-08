package br.com.rony.academico.sistema_academico.mapper;

import br.com.rony.academico.sistema_academico.dto.request.UsuarioRequestDTO;
import br.com.rony.academico.sistema_academico.dto.response.UsuarioResponseDTO;
import br.com.rony.academico.sistema_academico.entity.Usuario;

import java.time.LocalDateTime;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .login(dto.getLogin())
                .senha(dto.getSenha())
                .perfil(dto.getPerfil())
                .cadastro(LocalDateTime.now())
                .status("A")
                .build();
    }

    public static UsuarioResponseDTO toDTO(Usuario entity) {
        return UsuarioResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .login(entity.getLogin())
                .perfil(entity.getPerfil())
                .cadastro(entity.getCadastro())
                .status(entity.getStatus())
                .build();
    }
}
