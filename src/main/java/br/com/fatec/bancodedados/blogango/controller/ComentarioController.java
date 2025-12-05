package br.com.fatec.bancodedados.blogango.controller;

import br.com.fatec.bancodedados.blogango.dto.ComentarioCreateDTO;
import br.com.fatec.bancodedados.blogango.dto.ComentarioEditDTO;
import br.com.fatec.bancodedados.blogango.model.Comentario;
import br.com.fatec.bancodedados.blogango.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

  @Autowired()
  private ComentarioService comentarioService;

  @GetMapping("/{postId}")
  public ResponseEntity<Page<Comentario>> listarPorPost(
          @PathVariable String postId,
          @PageableDefault(page = 0, size = 10, sort = "dataCriacao", direction = Sort.Direction.DESC)Pageable pageable
          ){
      return ResponseEntity.ok(comentarioService.listarComentariosPorPost(postId, pageable));
  }

  @PostMapping()
  public ResponseEntity<Comentario> criarComentario(
          @RequestBody @Valid ComentarioCreateDTO comentario){
    Comentario novoComentario = this.comentarioService.criarComentario(comentario);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(novoComentario.getId())
            .toUri();

    return ResponseEntity.created(uri).body(null);
  }

  @DeleteMapping("/{comentarioId}")
  public ResponseEntity<Void> deletarComentario(
          @PathVariable String comentarioId
  ){
    this.comentarioService.deletarComentario(comentarioId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{comentarioId}")
  public ResponseEntity<Void> editarComentario(
          @PathVariable String comentarioId,
          @RequestBody @Valid ComentarioEditDTO comentarioEditDTO
  ){
    this.comentarioService.editarComentario(comentarioId, comentarioEditDTO);
    return ResponseEntity.noContent().build();
  }
}
