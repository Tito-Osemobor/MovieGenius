package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import com.titoosemobor.moviegenius.Exception.MovieException;
import com.titoosemobor.moviegenius.Service.TMDB.TMDBApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/moviegenius/tmdb")
public class TMDBController {
  @Autowired
  private TMDBApiService tmdbApiService;

  @GetMapping("/movie/{id}")
  public ResponseEntity<?> getMovieById(@PathVariable Long id) {
    try {
      Optional<MovieDTO> movie = tmdbApiService.fetchMovieById(id);
      return ResponseEntity.ok(movie);
    } catch (MovieException.MovieNotFoundException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @GetMapping("/genre/{id}")
  public ResponseEntity<?> getMovies(@PathVariable Long id) {
    try {
      Optional<Set<MovieDTO>> movies = tmdbApiService.fetchMoviesByGenre(id);
      return ResponseEntity.ok(movies);
    } catch (MovieException.GenreNotFoundException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }
}
