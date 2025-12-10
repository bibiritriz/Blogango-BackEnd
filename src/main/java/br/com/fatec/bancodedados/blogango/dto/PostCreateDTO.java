package br.com.fatec.bancodedados.blogango.dto;

import br.com.fatec.bancodedados.blogango.model.Categoria;
import br.com.fatec.bancodedados.blogango.model.StatusPost;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PostCreateDTO (
        @NotBlank(message = "Título é obrigatório")
        @Size(min = 10, max = 200, message = "Título deve ter entre 10 e 200 caracteres")
        String titulo,

        @NotBlank(message = "Conteúdo é obrigatório")
        @Size(min = 50, max = 5000, message = "Conteúdo deve ter entre 50 e 5000 caracteres")
        String conteudo,

        @NotBlank(message = "Autor é obrigatório")
        @Size(min = 3, max = 100, message = "Autor deve ter entre 3 e 100 caracteres")
        String autor,

        @NotNull(message = "Status é obrigatório")
        StatusPost status,

        @NotNull(message = "Categorias são obrigatórias")
        @Size(min=1, max=5, message = "Deve haver ao menos 1 id de categoria em categorias")
        List<Categoria> categorias) {
}
