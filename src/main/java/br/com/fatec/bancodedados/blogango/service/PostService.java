package br.com.fatec.bancodedados.blogango.service;

import br.com.fatec.bancodedados.blogango.dto.PostCreateDTO;
import br.com.fatec.bancodedados.blogango.dto.PostUpdateDTO;
import br.com.fatec.bancodedados.blogango.exception.ResourceNotFoundException;
import br.com.fatec.bancodedados.blogango.mapper.PostMapper;
import br.com.fatec.bancodedados.blogango.model.Post;
import br.com.fatec.bancodedados.blogango.model.StatusPost;
import br.com.fatec.bancodedados.blogango.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.Instant;
import java.util.regex.Pattern;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CategoriaService categoriaService;

    public Page<Post> listarPostsPublicos(Pageable pageable){
        return postRepository.findByStatusOrderByDataCriacaoDesc(StatusPost.PUBLICADO, pageable);
    }

    public Page<Post> listarPorCategoria(String categoriaNome, Pageable pageable) {
        return postRepository.findByStatusAndCategorias_NomeOrderByDataCriacaoDesc(StatusPost.PUBLICADO, categoriaNome, pageable);
    }

    public Page<Post> listarPorTitulo(String termo, Pageable pageable) {
        return postRepository.findByStatusAndTituloContainingIgnoreCaseOrderByDataCriacaoDesc(StatusPost.PUBLICADO, termo, pageable);
    }

    private String gerarSlug(String titulo){
        long quantidade = postRepository.countBySlug(titulo);

        String tituloNormalizado = Normalizer.normalize(titulo, Normalizer.Form.NFD);

        Pattern pattern = Pattern.compile("\\p{M}");
        String semAcento = pattern.matcher(tituloNormalizado).replaceAll("");

        String slugPadrao = semAcento.toLowerCase().replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "").trim();

        if(quantidade > 0){
            String sufixoSlug = "-" + quantidade;
            return slugPadrao.concat(sufixoSlug);
        }

        return slugPadrao;
    }

    public Post criarPost(PostCreateDTO dto){
        Post novoPost = postMapper.toEntity(dto);

        novoPost.setCategorias(dto.categorias());
        novoPost.setStatus(dto.status());
        novoPost.setSlug(gerarSlug(novoPost.getTitulo()));

        return postRepository.save(novoPost);
    }

    public Post obterPost(String id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post com id " + id + " não encontrado"));

        post.setVisualizacoes(post.getVisualizacoes() + 1);

        postRepository.save(post);

        return post;
    }

    public Post obterPostPorSlug(String slug){
        Post post = postRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Post com slug " + slug + " não encontrado"));

        post.setVisualizacoes(post.getVisualizacoes() + 1);

        postRepository.save(post);

        return post;
    }

    public void atualizarPost(String id, PostUpdateDTO post){
        Post postEncontrado = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post com id " + id + " não encontrado"));

        postMapper.updateEntityFromDto(post, postEncontrado);

        postEncontrado.setCategorias(post.categorias());
        postEncontrado.setDataAtualizacao(Instant.now());
        postEncontrado.setSlug(gerarSlug(post.titulo()));

        postRepository.save(postEncontrado);
    }

    public void deletarPost(String id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post com id " + id + " não encontrado"));

        post.setStatus(StatusPost.ARQUIVADO);

        postRepository.save(post);
    }
}
