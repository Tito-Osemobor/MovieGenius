package com.titoosemobor.moviegenius.TMDB;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TMDBApiController {
  private final TMDBApiService TMDBApiService;

  public TMDBApiController(TMDBApiService TMDBApiService) {
    this.TMDBApiService = TMDBApiService;
  }

  @GetMapping("/movies/{id}")
  public ResponseEntity<?> getMovieById(@PathVariable Long id) {
    Optional<MovieDTO> movie = TMDBApiService.fetchMovieById(id);
    if (movie.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(movie);
    }
  }
}
