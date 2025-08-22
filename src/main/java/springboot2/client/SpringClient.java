package springboot2.client;

import lombok.extern.log4j.Log4j2;
import springboot2.domain.Anime;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/2", Anime.class);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/2", Anime.class);
        log.info(object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange(
                "http://localhost:8080/animes/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Anime>>() {}
        );
        log.info(exchange.getBody());

//        Anime anime = Anime.builder().name("Bleach").build();
//        Anime animeSaved = new RestTemplate().postForObject("http://localhost:8080/animes", anime, Anime.class);
//        log.info(animeSaved);

        Anime anime = Anime.builder().name("Naruto").build();
        ResponseEntity<Anime> animeSaved = new RestTemplate().exchange(
                "http://localhost:8080/animes",
                HttpMethod.POST,
                new HttpEntity<>(anime, createJsonHeaders()),
                Anime.class
        );
        log.info(animeSaved.getBody());

        Anime animeToUpdate = animeSaved.getBody();
        if (animeToUpdate != null) {
            animeToUpdate.setName("Naruto Shippuden");
        }
        ResponseEntity<Void> animeUpdated = new RestTemplate().exchange(
                "http://localhost:8080/animes",
                HttpMethod.PUT,
                new HttpEntity<>(animeToUpdate, createJsonHeaders()),
                Void.class
        );
        log.info(animeUpdated);

        ResponseEntity<Void> animeDeleted = new RestTemplate().exchange(
                "http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                animeToUpdate != null ? animeToUpdate.getId() : null
        );
        log.info(animeDeleted);
    }

    private static HttpHeaders createJsonHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
