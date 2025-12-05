package br.com.fatec.bancodedados.blogango.service;

import br.com.fatec.bancodedados.blogango.exception.ResourceNotFoundException;
import br.com.fatec.bancodedados.blogango.model.Categoria;
import br.com.fatec.bancodedados.blogango.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    private String gerarSlug(String nome){
        String nomeNormalizado = Normalizer.normalize(nome, Normalizer.Form.NFD);

        Pattern pattern = Pattern.compile("\\p{M}");
        String nomeSemAcento = pattern.matcher(nomeNormalizado).replaceAll("");

        return nomeSemAcento.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "").trim();
    }

    public Categoria criarCategoria(Categoria categoria){
        categoria.setSlug(gerarSlug(categoria.getNome()));
        return categoriaRepository.save(categoria);
    }

    public Categoria obterCategoria(String id){
        return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria com id " + id + " não encontrada"));
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    public List<Categoria> buscarCategoriasPorId(List<String> ids){
        return categoriaRepository.findAllById(ids);
    }
}
