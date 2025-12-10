package br.com.fatec.bancodedados.blogango.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comentarios")
public class Comentario {
  @Id
  private String id;

  @NotBlank(message = "ID do post é obrigatório")
  private String postId;

  @NotBlank(message = "Autor é obrigatório")
  @Size(min = 3, max = 100, message = "Autor deve ter no mínimo 3 caracteres")
  private String autor;

  @NotBlank(message = "Usuario é obrigatório")
  @Indexed(unique = true)
  private String usuario;

  @NotBlank(message = "Conteúdo é obrigatório")
  @Size(min = 5,max = 1000, message = "O conteúdo deve ter no mínimo 5 caracteres")
  private String conteudo;

  private LocalDateTime dataCriacao = LocalDateTime.now();
  private Boolean aprovado = false;
}
