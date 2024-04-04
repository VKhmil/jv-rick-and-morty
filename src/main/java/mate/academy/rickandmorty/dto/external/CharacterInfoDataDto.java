package mate.academy.rickandmorty.dto.external;

public record CharacterInfoDataDto(
        Integer count,
        Integer pages,
        String next,
        String prev
) {
}
