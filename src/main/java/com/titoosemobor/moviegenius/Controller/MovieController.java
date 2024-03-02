package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import com.titoosemobor.moviegenius.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/moviegenius/movies")
public class MovieController {
  @Autowired
  private MovieService movieService;

  @GetMapping
  public ResponseEntity<?> getMoviesByGenre(@RequestParam("genres")Set<String> genreId) {
    return ResponseEntity.ok(movieService.fetchMoviesByGenre(genreId));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getMovieById(@PathVariable Long id) {
    return ResponseEntity.ok(movieService.fetchMovieById(id));
  }

  @GetMapping("/posters")
  public ResponseEntity<?> getRandomPosters() {
    return ResponseEntity.ok(movieService.fetchRandomPosters());
  }
}
