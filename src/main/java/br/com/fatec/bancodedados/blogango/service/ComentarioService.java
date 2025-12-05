package br.com.fatec.bancodedados.blogango.service;

import br.com.fatec.bancodedados.blogango.dto.ComentarioCreateDTO;
import br.com.fatec.bancodedados.blogango.dto.ComentarioEditDTO;
import br.com.fatec.bancodedados.blogango.exception.ResourceNotFoundException;
import br.com.fatec.bancodedados.blogango.model.Comentario;
import br.com.fatec.bancodedados.blogango.model.Post;
import br.com.fatec.bancodedados.blogango.repository.ComentarioRepository;
import br.com.fatec.bancodedados.blogango.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

  @Autowired
  private ComentarioRepository comentarioRepository;

  @Autowired
  private PostRepository postRepository;

  public Page<Comentario> listarComentariosPorPost(String postId, Pageable pageable){
    return this.comentarioRepository.findByPostId(postId, pageable);
  }

  public Comentario criarComentario(ComentarioCreateDTO comentarioCreateDTO){
    Comentario comentario = new Comentario();
    comentario.setPostId(comentarioCreateDTO.postId());
    comentario.setAutor(comentarioCreateDTO.autor());
    comentario.setEmail(comentarioCreateDTO.email());
    comentario.setConteudo(comentarioCreateDTO.conteudo());
    return this.comentarioRepository.save(comentario);
  }

  public void deletarComentario(String comentarioId){
    boolean isExistente = this.comentarioRepository.existsById(comentarioId);

    if(!isExistente){
      throw new ResourceNotFoundException("Comentário não encontrado com ID: " + comentarioId);
    }

    this.comentarioRepository.deleteById(comentarioId);
  }

  public void editarComentario(String comentarioId, ComentarioEditDTO comentarioEditDTO){
    Comentario comentario = this.comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado com ID: " + comentarioId));

    comentario.setAutor(comentarioEditDTO.autor());
    comentario.setEmail(comentarioEditDTO.email());
    comentario.setConteudo(comentarioEditDTO.conteudo());

    this.comentarioRepository.save(comentario);

  }

  public void aprovarComentario(String comentarioId){
    Comentario comentario = this.comentarioRepository.findById(comentarioId).orElseThrow(
            () -> new ResourceNotFoundException("Comentário não encontrado com ID: " + comentarioId));

    comentario.setAprovado(true);
    this.comentarioRepository.save(comentario);

    Post post = postRepository.findById(comentario.getPostId()).orElseThrow(
            () -> new ResourceNotFoundException("Post não encontrado com ID: " + comentario.getPostId())
    );

    Long quantidadeComentarios = this.comentarioRepository.countByPostIdAndAprovado(post.getId(), true);
    post.setComentariosCount(quantidadeComentarios);

    this.postRepository.save(post);
  }
}
