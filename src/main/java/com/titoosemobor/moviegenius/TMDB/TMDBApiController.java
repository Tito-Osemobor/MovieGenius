package com.titoosemobor.moviegenius.TMDB;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import com.titoosemobor.moviegenius.Exception.MovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/tmdb")
public class TMDBApiController {
  @Autowired
  private TMDBApiService tmdbApiService;

  @GetMapping("/movies/{id}")
  public ResponseEntity<?> getMovieById(@PathVariable Long id) {
    try {
      Optional<MovieDTO> movie = tmdbApiService.fetchMovieById(id);
      return ResponseEntity.ok(movie);
    } catch (MovieException.MovieNotFoundException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @GetMapping("/movies/genres/{id}")
  public ResponseEntity<?> getMovies(@PathVariable Integer id) {
    try {
      Optional<Set<MovieDTO>> movies = tmdbApiService.fetchMoviesByGenre(id);
      return ResponseEntity.ok(movies);
    } catch (MovieException.GenreNotFoundException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }
}
