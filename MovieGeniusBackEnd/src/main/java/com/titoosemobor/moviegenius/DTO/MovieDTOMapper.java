package com.titoosemobor.moviegenius.DTO;

import com.titoosemobor.moviegenius.Entity.Genre;
import com.titoosemobor.moviegenius.Entity.Movie;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MovieDTOMapper implements Function<Movie, MovieDTO> {

  public static final MovieDTOMapper INSTANCE = new MovieDTOMapper();
  @Override
  public MovieDTO apply(Movie movie) {
    return new MovieDTO(
      movie.getTitle(),
      movie.getGenres().stream()
        .map(Genre::getName)
        .collect(Collectors.toSet()),
      "https://image.tmdb.org/t/p/original" + movie.getBackdropPath(),
      "https://image.tmdb.org/t/p/original" + movie.getPosterPath(),
      movie.getOverview(),
      "https://www.youtube.com/watch?v=" + movie.getTrailer()
    );
  }
}
