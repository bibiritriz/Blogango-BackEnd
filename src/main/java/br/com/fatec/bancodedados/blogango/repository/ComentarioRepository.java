package br.com.fatec.bancodedados.blogango.repository;

import br.com.fatec.bancodedados.blogango.model.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentario, String> {
  Page<Comentario> findByPostId(String postId, Pageable pageable);


}
