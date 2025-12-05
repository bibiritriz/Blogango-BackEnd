package br.com.fatec.bancodedados.blogango.service;

import br.com.fatec.bancodedados.blogango.model.Categoria;
import br.com.fatec.bancodedados.blogango.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    private String gerarSlug(String nome){
        return nome.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "").trim();
    }

    public Categoria criarCategoria(Categoria categoria){
        categoria.setSlug(gerarSlug(categoria.getNome()));
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }
}
