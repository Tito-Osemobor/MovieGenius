package com.titoosemobor.moviegenius.Controller;

import com.titoosemobor.moviegenius.DTO.MovieDTO;
import com.titoosemobor.moviegenius.Entity.Movie;
import com.titoosemobor.moviegenius.Exception.MovieException;
import com.titoosemobor.moviegenius.Service.MovieService;
import com.titoosemobor.moviegenius.TMDB.TMDBApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/moviegenius/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private TMDBApiService tmdbApiService;

    @GetMapping
    public ResponseEntity<?> getMoviesByGenre(@RequestParam("genres")Set<String> genreId) {
      Set<MovieDTO> movies = movieService.fetchMoviesByGenre(genreId);
      return ResponseEntity.ok(movies);
    }

//    @GetMapping("/movies/{id}")
//    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
//      try {
//        Optional<MovieDTO> movie = tmdbApiService.fetchMovieById(id);
//        return ResponseEntity.ok(movie);
//      } catch (MovieException.MovieNotFoundException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//      }
//    }
//
//    @GetMapping("/movies/genres/{id}")
//    public ResponseEntity<?> getMovies(@PathVariable Integer id) {
//      try {
//        Optional<Set<MovieDTO>> movies = tmdbApiService.fetchMoviesByGenre(id);
//        return ResponseEntity.ok(movies);
//      } catch (MovieException.GenreNotFoundException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//      }
//    }
}
