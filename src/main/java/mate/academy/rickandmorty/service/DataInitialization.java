package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.character.CharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialization {
    private final CharacterClient client;
    private final CharacterService characterService;
    private final CharacterMapper mapper;

    @PostConstruct
    public void init() {
        List<ExternalCharacterDataDto> characters = client.getCharacters();
        List<Character> characterModels = mapper.toModels(characters);
    }
}
