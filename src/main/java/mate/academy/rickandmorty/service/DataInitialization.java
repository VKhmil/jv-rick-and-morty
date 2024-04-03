package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
        List<Character> characters = client.getCharacters().stream()
                .map(mapper::toModel)
                .toList();
        characterService.saveAll(characters);
    }
}
