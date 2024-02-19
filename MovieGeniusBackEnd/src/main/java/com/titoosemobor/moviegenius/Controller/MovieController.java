package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import com.titoosemobor.moviegenius.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/moviegenius/movies")
public class MovieController {
  @Autowired
  private MovieService movieService;

  @GetMapping
  public ResponseEntity<?> getMoviesByGenre(@RequestParam("genres")Set<String> genreId) {
    Set<MovieDTO> movies = movieService.fetchMoviesByGenre(genreId);
    return ResponseEntity.ok(movies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getMovieById(@PathVariable Long id) {
    MovieDTO movie = movieService.fetchMovieById(id);
    return ResponseEntity.ok(movie);
  }
}
