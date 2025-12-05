package br.com.fatec.bancodedados.blogango.controller;

import br.com.fatec.bancodedados.blogango.dto.PostCreateDTO;
import br.com.fatec.bancodedados.blogango.dto.PostUpdateDTO;
import br.com.fatec.bancodedados.blogango.mapper.PostMapper;
import br.com.fatec.bancodedados.blogango.model.Categoria;
import br.com.fatec.bancodedados.blogango.model.Post;
import br.com.fatec.bancodedados.blogango.service.CategoriaService;
import br.com.fatec.bancodedados.blogango.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<Post>> listarPosts(
            @PageableDefault(size = 10, page = 0, sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(postService.listarPostsPublicos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> obterPost(@PathVariable String id) {
        return ResponseEntity.ok(postService.obterPost(id));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<Page<Post>> listarPorCategoria(
            @PathVariable String categoriaId,
            @PageableDefault(size = 10, page = 0, sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Categoria categoria = categoriaService.obterCategoria(categoriaId);

        return ResponseEntity.ok(postService.listarPorCategoria(categoria.getNome(), pageable));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Post>> buscarPorTitulo(
            @RequestParam String termo,
            @PageableDefault(size = 10, page = 0, sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(postService.listarPorTitulo(termo, pageable));
    }

    @PostMapping
    public ResponseEntity<String> criarPost(@RequestBody @Valid PostCreateDTO dto) {
        Post novoPost = postService.criarPost(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoPost.getId())
                .toUri();

        return ResponseEntity.created(uri).body(novoPost.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarPost(@PathVariable String id, @RequestBody @Valid PostUpdateDTO dto) {
        postService.atualizarPost(id, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable String id) {
        postService.deletarPost(id);
        return ResponseEntity.noContent().build();
    }
}
