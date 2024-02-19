package com.titoosemobor.moviegenius.Service.TMDB;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TMDBApiClient {
  static Dotenv dotenv = Dotenv.configure().load();
  private final String BASE_URL = "https://api.themoviedb.org/3";
  private final String TMDB_BEARER_TOKEN = dotenv.get("TMDB_BEARER_TOKEN");
  private WebClient webClient;

  public TMDBApiClient () {
    this.webClient = WebClient.builder()
      .baseUrl(BASE_URL)
      .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + TMDB_BEARER_TOKEN)
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .build();
  }

  public WebClient getWebClient() {
    return webClient;
  }
}
