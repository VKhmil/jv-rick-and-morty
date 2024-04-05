package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterInternalDto toDto(Character character);

    @Mapping(source = "id", target = "externalId")
    Character toModel(ExternalCharacterDataDto dto);

    @Mapping(source = "id", target = "externalId")
    List<Character> toModels(List<ExternalCharacterDataDto> dtos);
}
