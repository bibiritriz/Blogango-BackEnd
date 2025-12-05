package br.com.fatec.bancodedados.blogango.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "categorias")
public class Categoria {
    @Id
    private String id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 10, message = "Nome deve ter entre 10 e 200 caracteres")
    private String nome;

    @Size(max = 200, message = "Descrição deve ter entre 10 e 200 caracteres")
    private String descricao;

    private String slug;
}
