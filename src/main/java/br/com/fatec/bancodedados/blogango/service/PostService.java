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

import java.time.Instant;
import java.time.LocalDateTime;

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

        String quantidadeParaSlug = "-" + String.valueOf(quantidade);

        String slugPadrao = titulo.toLowerCase().replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "").trim();

        if(quantidade > 0){
            return slugPadrao.concat(quantidadeParaSlug);
        }

        return slugPadrao;
    }

    public Post criarPost(PostCreateDTO dto){
        Post novoPost = postMapper.toEntity(dto);

        novoPost.setCategorias(categoriaService.buscarCategoriasPorId(dto.categorias()));
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

    public void atualizarPost(String id, PostUpdateDTO post){
        Post postEncontrado = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post com id " + id + " não encontrado"));

        postMapper.updateEntityFromDto(post, postEncontrado);

        postEncontrado.setCategorias(categoriaService.buscarCategoriasPorId(post.categorias()));
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
