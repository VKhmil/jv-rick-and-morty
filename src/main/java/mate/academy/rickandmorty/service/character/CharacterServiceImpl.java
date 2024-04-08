package mate.academy.rickandmorty.service.character;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String EXC_MSG_CANT_FIND = "Can't find character with id ";
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private long numberOfCharacters;

    @Override
    public void saveAll(List<Character> characters) {
        characterRepository.saveAll(characters);
    }

    @Override
    public CharacterInternalDto getRandomCharacter() {
        List<Character> allCharacters = characterRepository.findAll();
        if (allCharacters.isEmpty()) {
            throw new RuntimeException("No characters found in the database.");
        }

        int randomIndex = random.nextInt(allCharacters.size());
        Character randomCharacter = allCharacters.get(randomIndex);
        return mapper.toDto(randomCharacter);
    }

    @Override
    public List<CharacterInternalDto> getAllByNamePart(String namePart) {
        List<Character> characters = characterRepository
                .findAllByNameContainingIgnoreCase(namePart);
        return mapper.toModels(characters);

    }
}
