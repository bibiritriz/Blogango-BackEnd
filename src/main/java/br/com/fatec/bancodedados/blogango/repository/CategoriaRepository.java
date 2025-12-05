package br.com.fatec.bancodedados.blogango.repository;

import br.com.fatec.bancodedados.blogango.model.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {
    int findBySlug(String slug);
}
