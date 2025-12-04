package br.com.fatec.bancodedados.blogango.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comentarios")
public class Comentario {
  @Id
  private String id;

  @NotBlank(message = "ID do post é obrigatório")
  private String postId;

  @NotBlank(message = "Autor é obrigatório")
  @Size(min = 3, max = 100, message = "Autor deve ter no mínimo 3 caracteres")
  private String autor;

  @NotBlank(message = "Email é obrigatório")
  @Email(message = "Email deve ser válido")
  private String email;

  @NotBlank(message = "Conteúdo é obrigatório")
  @Size(min = 5,max = 1000, message = "O conteúdo deve ter no mínimo 5 caracteres")
  private String conteudo;

  private LocalDateTime dataCriacao = LocalDateTime.now();
  private Boolean aprovado = false;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPostId() {
    return postId;
  }

  public void setPostId(String postId) {
    this.postId = postId;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }

  public LocalDateTime getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public Boolean getAprovado() {
    return aprovado;
  }

  public void setAprovado(Boolean aprovado) {
    this.aprovado = aprovado;
  }
}
