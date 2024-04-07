package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class CharacterInfoDataDto {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}
