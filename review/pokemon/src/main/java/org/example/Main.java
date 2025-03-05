package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.github.Github;
import org.example.pokemon.Pokemon;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Github github = new Github();
        String path = "https://pokeapi.co/api/v2/pokemon/%d";
        Random rand = new Random();
        int pokemonId = rand.nextInt(1, 152);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path.formatted(pokemonId))).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        ObjectMapper objectMapper = new ObjectMapper();
        Pokemon pokemon = objectMapper.readValue(response.body(), Pokemon.class);
//        System.out.println(pokemon);

        // 포켓몬 정보 출력
        System.out.println("Pokemon ID: " + pokemon.getId());
        System.out.println("Pokemon Name: " + pokemon.getName());
        System.out.println("Height: " + pokemon.getHeight());
        System.out.println("Weight: " + pokemon.getWeight());

        // 포켓몬 타입 출력
        System.out.println("Types: ");
        for (Pokemon.Type type : pokemon.getTypes()) {
            System.out.println(" - " + type.getType().getName());
        }

        // 포켓몬 능력 출력
        System.out.println("Abilities: ");
        for (Pokemon.Ability ability : pokemon.getAbilities()) {
            System.out.println(" - " + ability.getAbility().getName());
        }

        // 포켓몬 능력치 출력
        System.out.println("Stats: ");
        for (Pokemon.Stat stat : pokemon.getStats()) {
            System.out.println(" - " + stat.getStat().getName() + ": " + stat.getBaseStat());
        }

        // 포켓몬 스프라이트 출력 (이미지 URL)
        System.out.println("Image URL: " + pokemon.getSprites().getFrontDefault());

        // GitHub 이슈 생성
        github.createGitHubIssue(
                pokemon.getName(),
                pokemon.getSprites().getFrontDefault(),
                pokemon.getTypes(),
                String.valueOf(pokemon.getHeight()),
                String.valueOf(pokemon.getWeight()),
                pokemon.getStats().get(0).getBaseStat(),  // HP 예시로 첫 번째 능력치 사용
                pokemon.getStats().get(1).getBaseStat(),  // Attack 예시로 두 번째 능력치 사용
                pokemon.getStats().get(2).getBaseStat(),  // Defense 예시로 세 번째 능력치 사용
                pokemon.getStats().get(3).getBaseStat(),  // Special Attack 예시로 네 번째 능력치 사용
                pokemon.getStats().get(4).getBaseStat(),  // Special Defense 예시로 다섯 번째 능력치 사용
                pokemon.getStats().get(5).getBaseStat(),  // Speed 예시로 여섯 번째 능력치 사용
                "Previous Evolution", // 진화 전 정보 (예시로 사용)
                "Next Evolution"      // 진화 후 정보 (예시로 사용)
        );
    }
}