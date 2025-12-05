package br.com.fatec.bancodedados.blogango.controller;

import br.com.fatec.bancodedados.blogango.dto.CategoriaCreateDTO;
import br.com.fatec.bancodedados.blogango.model.Categoria;
import br.com.fatec.bancodedados.blogango.service.CategoriaService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<String> criarCategoria(@RequestBody @Valid @NotNull CategoriaCreateDTO dto) {
        Categoria categoria = new Categoria();

        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());

        categoria = categoriaService.criarCategoria(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(uri).body(uri);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
}
