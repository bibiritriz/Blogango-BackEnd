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

    private static final List<String> CORES_PADRAO = List.of(
            "24CE6B",
            "F65E61",
            "5E97F6",
            "FFD000"
    );

    private String gerarSlug(String nome){
        String nomeNormalizado = Normalizer.normalize(nome, Normalizer.Form.NFD);

        Pattern pattern = Pattern.compile("\\p{M}");
        String nomeSemAcento = pattern.matcher(nomeNormalizado).replaceAll("");

        return nomeSemAcento.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "").trim();
    }

    public Categoria criarCategoria(Categoria categoria){
        categoria.setSlug(gerarSlug(categoria.getNome()));

        String corSelecionada = this.definirCorAutomatica();

        categoria.setCor(corSelecionada);
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

    private String definirCorAutomatica() {
        List<String> coresUsadas = this.categoriaRepository.findDistinctCorBy();

        List<String> coresDisponiveis = CORES_PADRAO.stream()
                .filter(cor -> !coresUsadas.contains(cor))
                .toList();

        if (coresDisponiveis.isEmpty()) {
            int indexAleatorio = (int) (Math.random() * CORES_PADRAO.size());
            return CORES_PADRAO.get(indexAleatorio);
        }

        return coresDisponiveis.get(0);
    }
}
