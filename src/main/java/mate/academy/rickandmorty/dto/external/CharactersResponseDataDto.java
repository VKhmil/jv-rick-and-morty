package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharactersResponseDataDto {
    private List<ExternalCharacterDataDto> results;
}
