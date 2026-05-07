package br.com.rony.academico.sistema_academico.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe padrão para respostas da API.
 * Segue o padrão definido nas orientações do projeto:
 * - Sucesso: { "sucesso": true, "dados": {} }
 * - Erro: { "sucesso": false, "mensagem": "..." }
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean sucesso;
    private T dados;
    private String mensagem;

    public static <T> ApiResponse<T> sucesso(T dados) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSucesso(true);
        response.setDados(dados);
        return response;
    }

    public static <T> ApiResponse<T> erro(String mensagem) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSucesso(false);
        response.setMensagem(mensagem);
        return response;
    }
}
