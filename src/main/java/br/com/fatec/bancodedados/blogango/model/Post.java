package br.com.fatec.bancodedados.blogango.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "posts")
public class Post {
    @Id
    private String id;

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 10, max = 200, message = "Título deve ter entre 10 e 200 caracteres")
    private String titulo;


    private String slug;

    @NotBlank(message = "Conteúdo é obrigatório")
    @Size(min = 50, max = 5000, message = "Conteúdo deve ter entre 50 e 5000 caracteres")
    private String conteudo;

    @NotBlank(message = "Autor é obrigatório")
    @Size(min = 3, max = 100, message = "Autor deve ter entre 3 e 100 caracteres")
    private String autor;

    @NotNull(message = "Categoria é obrigatória")
    @Size(min=1, max=5)
    private List<Categoria> categorias;

    private LocalDateTime dataCriacao = LocalDateTime.now();
    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    private StatusPost status = StatusPost.RASCUNHO;

    private Long visualizacoes = 0L;
    private Long comentariosCount = 0L;
}
