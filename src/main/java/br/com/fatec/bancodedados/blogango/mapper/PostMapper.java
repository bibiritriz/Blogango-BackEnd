package br.com.fatec.bancodedados.blogango.mapper;

import br.com.fatec.bancodedados.blogango.dto.PostCreateDTO;
import br.com.fatec.bancodedados.blogango.dto.PostUpdateDTO;
import br.com.fatec.bancodedados.blogango.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "categorias", ignore = true)
    Post toEntity(PostCreateDTO dto);

    @Mapping(target = "categorias", ignore = true)
    void updateEntityFromDto(PostUpdateDTO dto, @MappingTarget Post post);
}
