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
        if (numberOfCharacters == 0) {
            numberOfCharacters = characterRepository.count();
        }

        long randomId = random.nextLong(numberOfCharacters);
        Character character =
                characterRepository.findById(randomId)
                        .orElseThrow(() -> new RuntimeException(EXC_MSG_CANT_FIND + randomId));
        return mapper.toDto(character);
    }

    @Override
    public List<CharacterInternalDto> getAllByNamePart(String namePart) {
        return characterRepository
                .findAllByNameContainingIgnoreCase(namePart)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
