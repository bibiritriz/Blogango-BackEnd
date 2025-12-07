package br.com.fatec.bancodedados.blogango.mapper;

import br.com.fatec.bancodedados.blogango.dto.ComentarioCreateDTO;
import br.com.fatec.bancodedados.blogango.dto.ComentarioUpdateDTO;
import br.com.fatec.bancodedados.blogango.model.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

  Comentario toEntity(ComentarioCreateDTO dto);

  void updateEntityFromDto(ComentarioUpdateDTO  dto, @MappingTarget Comentario comentario);
}
