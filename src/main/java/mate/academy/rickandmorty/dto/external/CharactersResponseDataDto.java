package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharactersResponseDataDto {
    private CharacterInfoDataDto info;
    private List<ExternalCharacterDataDto> results;
}
