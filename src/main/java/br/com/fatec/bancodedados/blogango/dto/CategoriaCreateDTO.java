package br.com.fatec.bancodedados.blogango.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;

public record CategoriaCreateDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
        String nome,

        @Size(max = 200, message = "Descrição não pode exceder 200 caracteres")
        String descricao) {
}
