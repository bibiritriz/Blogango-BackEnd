package br.com.fatec.bancodedados.blogango.repository;

import br.com.fatec.bancodedados.blogango.model.Post;
import br.com.fatec.bancodedados.blogango.model.StatusPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    Page<Post> findByStatusOrderByDataCriacaoDesc(StatusPost status, Pageable pageable);

    Page<Post> findByStatusAndCategorias_NomeOrderByDataCriacaoDesc(StatusPost status, String categoriaNome, Pageable pageable);

    Page<Post> findByStatusAndTituloContainingIgnoreCaseOrderByDataCriacaoDesc(StatusPost status, String titulo, Pageable pageable);

    long countBySlug(String slug);
}
