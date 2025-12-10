package br.com.fatec.bancodedados.blogango.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ComentarioUpdateDTO(
    @NotBlank(message = "Autor é obrigatório")
    @Size(min = 3, max = 100, message = "Autor deve ter no mínimo 3 caracteres")
    String autor,
    @NotBlank(message = "Usuario é obrigatório")
    String usuario,
    @NotBlank(message = "Conteúdo é obrigatório")
    @Size(min = 5,max = 1000, message = "O conteúdo deve ter no mínimo 5 caracteres")
    String conteudo
){}
