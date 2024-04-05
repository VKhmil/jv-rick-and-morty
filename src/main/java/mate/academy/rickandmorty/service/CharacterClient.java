package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharactersResponseDataDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character?page=%d";
    private HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public List<ExternalCharacterDataDto> getCharacters() {
        List<ExternalCharacterDataDto> charactersFromApi = new ArrayList<>();
        httpClient = HttpClient.newHttpClient();
        String url = BASE_URL;

        while (url != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response =
                        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharactersResponseDataDto dataDto =
                        objectMapper.readValue(response.body(), CharactersResponseDataDto.class);
                charactersFromApi.addAll(dataDto.getResults());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return charactersFromApi;
    }
}
