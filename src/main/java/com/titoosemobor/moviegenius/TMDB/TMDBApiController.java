package com.titoosemobor.moviegenius.TMDB;

import com.titoosemobor.moviegenius.Entity.Movie;
import com.titoosemobor.moviegenius.Entity.Video;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TMDBApiController {
  private final TMDBApiService TMDBApiService;

  public TMDBApiController(TMDBApiService TMDBApiService) {
    this.TMDBApiService = TMDBApiService;
  }

  @GetMapping("/movies/{id}")
  public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
    Movie movie = TMDBApiService.fetchMovieById(id);
    if (movie == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(movie);
    }
  }

  @GetMapping("/movies/{id}/videos")
  public ResponseEntity<Video> getMovieVideo(@PathVariable Long id) {
    Video video = TMDBApiService.fetchMovieVideo(id);
    return ResponseEntity.ok(video);
  }
}
