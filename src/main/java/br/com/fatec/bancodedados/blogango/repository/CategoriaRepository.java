package br.com.fatec.bancodedados.blogango.repository;

import br.com.fatec.bancodedados.blogango.model.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {

  List<String> findDistinctCorBy();

}
