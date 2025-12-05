package br.com.fatec.bancodedados.blogango.service;

import br.com.fatec.bancodedados.blogango.dto.PostUpdateDTO;
import br.com.fatec.bancodedados.blogango.model.Categoria;
import br.com.fatec.bancodedados.blogango.model.Post;
import br.com.fatec.bancodedados.blogango.model.StatusPost;
import br.com.fatec.bancodedados.blogango.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Page<Post> listarPostsPublicos(Pageable pageable){
        return postRepository.findByStatusOrderByDataCriacaoDesc(StatusPost.PUBLICADO, pageable);
    }

    public Page<Post> listarPorCategoria(String categoriaNome, Pageable pageable) {
        return postRepository.findByStatusAndCategoria_NomeOrderByDataCriacaoDesc(StatusPost.PUBLICADO, categoriaNome, pageable);
    }

    public Page<Post> listarPorTitulo(String termo, Pageable pageable) {
        return postRepository.findByStatusAndTituloContainingIgnoreCaseOrderByDataCriacaoDesc(StatusPost.PUBLICADO, termo, pageable);
    }

    private String gerarSlug(String titulo){
        return titulo.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "").trim();
    }

    public Post criarPost(Post post){
        post.setSlug(gerarSlug(post.getTitulo()));
        return postRepository.save(post);
    }

    public Post obterPost(String id){
        Post post = postRepository.findById(id).orElseThrow();

        post.setVisualizacoes(post.getVisualizacoes() + 1);

        postRepository.save(post);

        return post;
    }

    public void atualizarPost(String id, PostUpdateDTO post){
        Post postEncontrado = postRepository.findById(id).orElseThrow();

        postEncontrado.setTitulo(post.titulo());
        postEncontrado.setDataAtualizacao(LocalDateTime.now());
        postEncontrado.setConteudo(post.conteudo());
        postEncontrado.setCategorias(post.categorias());
        postEncontrado.setSlug(gerarSlug(post.titulo()));
        postEncontrado.setStatus(post.status());

        postRepository.save(postEncontrado);
    }

    public void deletarPost(String id){
        Post post = postRepository.findById(id).orElseThrow();

        post.setStatus(StatusPost.ARQUIVADO);

        postRepository.save(post);
    }
}
