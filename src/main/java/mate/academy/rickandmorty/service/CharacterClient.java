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
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public List<ExternalCharacterDataDto> getCharacters() {
        List<ExternalCharacterDataDto> allCharacters = new ArrayList<>();
        String url = BASE_URL;
        try {
            while (url != null && !url.isEmpty()) {
                HttpResponse<String> response = fetchData(url);
                CharactersResponseDataDto pageData = objectMapper.readValue(response.body(),
                        CharactersResponseDataDto.class);
                allCharacters.addAll(pageData.getResults());
                url = pageData.getInfo().getNext();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return allCharacters;
    }

    public HttpResponse<String> fetchData(String url) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
